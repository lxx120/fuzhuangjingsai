package com.match.mybasit.utils;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.MappedStatement.Builder;

public class InterceptorUtil {
	/** 
     * 复制MappedStatement对象 
     */  
    public static  MappedStatement copyFromMappedStatement(MappedStatement ms,  
            SqlSource newSqlSource,ParameterMap parameterMap) {  
        Builder builder = new Builder(ms.getConfiguration(), ms.getId(),  
                newSqlSource, ms.getSqlCommandType());  
  
        builder.resource(ms.getResource());  
        builder.fetchSize(ms.getFetchSize());  
        builder.statementType(ms.getStatementType());  
        builder.keyGenerator(ms.getKeyGenerator());  
        // builder.keyProperty(ms.getKeyProperty());  
        builder.timeout(ms.getTimeout());  
        builder.parameterMap(parameterMap);  
        builder.resultMaps(ms.getResultMaps());  
        builder.resultSetType(ms.getResultSetType());  
        builder.cache(ms.getCache());  
        builder.flushCacheRequired(ms.isFlushCacheRequired());  
        builder.useCache(ms.isUseCache());  
  
        return builder.build();  
    }  
    /** 
     * 复制BoundSql对象 
     */  
    public static BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,  
            String sql) {  
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql,  
                boundSql.getParameterMappings(), boundSql.getParameterObject());  
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {  
            String prop = mapping.getProperty();  
            if (boundSql.hasAdditionalParameter(prop)) {  
                newBoundSql.setAdditionalParameter(prop, boundSql  
                        .getAdditionalParameter(prop));  
            }  
        }  
        return newBoundSql;  
    } 
}
