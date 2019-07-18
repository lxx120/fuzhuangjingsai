package com.match.mybasit.interceptors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.match.data.author.persist.Rules;
import com.match.mybasit.utils.DataAuthorPool;
import com.match.springutils.SpringContext;


@Intercepts({
	@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})	
})
public class DataAuthorInterceptor implements Interceptor {
	private Logger log=LogManager.getLogger();
	@Override
	public Object intercept(Invocation arg0) throws Throwable {
		if(arg0.getTarget() instanceof RoutingStatementHandler){
			StatementHandler handler = this.getActuralHandlerObject(arg0);
	        MetaObject metaStatementHandler = SystemMetaObject.forObject(handler);
			if(handler==null)
				return null;
			BoundSql boundSql = handler.getBoundSql();  
		    Object parameterObject = boundSql.getParameterObject();  
		    String originalSql = boundSql.getSql().trim();
		    Pattern wherePattern=Pattern.compile(" where ");
		    String oSqls[]=wherePattern.split(originalSql);
		    if(parameterObject instanceof HashMap){
		    	@SuppressWarnings("unchecked")
				Map<String,Object> params=(HashMap<String,Object>)parameterObject;
		    	Object dataAuthorIDObj=params.get("dataAuthorID");
		    	if(dataAuthorIDObj!=null){
		    		ApplicationContext ac=SpringContext.getAppContext();
		    		DataAuthorPool aup=ac.getBean(DataAuthorPool.class);
		    		Rules rul=aup.getByAuthorID(dataAuthorIDObj+"");
		    		String newSql=originalSql;
		    		if(oSqls.length>1){  //如果是没有where, 则不需要
		    			StringBuilder sb=new StringBuilder();
		    			for (int i = 0; i < oSqls.length; i++) {
							sb.append(oSqls[i]);
							sb.append(" where ");
							if(i==rul.getWhereIndex()){
								sb.append("("+rul.getSql()+")");
								sb.append(" and ");
							}
						}
		    			newSql=sb.toString();
		    		}
		    		 //修改当前要执行的sql语句
		            metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
		            //相当于调用prepare方法，预编译sql并且加入参
		            PreparedStatement ps = (PreparedStatement) arg0.proceed();
		            //获取sql总的参数总数
		            return ps;
		    	}
		    }
		}
		Object obj=arg0.proceed();
		return obj;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
		log.warn("prepare interceptor set properties do nothing!");
	}
	
	private StatementHandler getActuralHandlerObject(Invocation invocation) {
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();
		MetaObject metaStatementHandler = SystemMetaObject
				.forObject(statementHandler);
		Object object = null;
		// 分离代理对象链，目标可能被多个拦截器拦截，分离出最原始的目标类
		while (metaStatementHandler.hasGetter("h")) {
			object = metaStatementHandler.getValue("h");
			metaStatementHandler = SystemMetaObject.forObject(object);
		}

		if (object == null) {
			return statementHandler;
		}
		return (StatementHandler) object;
	}
	

}
