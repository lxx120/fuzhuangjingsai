package com.match.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.match.authors.IAuthorService;
import com.match.common.ConstantVar;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.springutils.Authon;
import com.match.systemconfig.persist.Login;

/**
 * 权限拦截器
 * @author mengly
 *
 */
public class AuthonInterceptor extends HandlerInterceptorAdapter{
	private Logger log=LogManager.getLogger();
	@Reference
	private IAuthorService authenSer;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean isAjax=false;
		if("XMLHttpRequest".equals(request.getHeader("X-Requested-With")))
			isAjax=true;
		try{
			if (handler instanceof HandlerMethod) {
		        HandlerMethod methodHandler = (HandlerMethod) handler;  
				Authon au=methodHandler.getMethod().getAnnotation(Authon.class);
				if(au!=null){
					String module=au.module();
					int opValue=au.operation();
		            Login login=(Login)request.getSession().getAttribute(ConstantVar.sessionLoginName);
		            if(login==null){
		            	if(isAjax){
		        			Gson g=new Gson();
		        			JsonObject jo=new JsonObject();
		        			jo.addProperty("code", 3);
		        			jo.addProperty("errmsg","您还没有登录!");
		        			SessionMethod.writeresp(response, g.toJson(jo));
		        			return false;
		        		}
		        		SessionMethod.sendRedirect(response, request, ConstantVar.beginLoginURL);
		        		return false;
		            }
					ObjectResult<Boolean> or=this.authenSer.checkAuthon(module, opValue, login.getUserid(), login.getMerchant());
					if(or.getCode()==0 && or.getItem()==true){
						return true;
					}else{
						if(isAjax){
							Gson g=new Gson();
							JsonObject jo=new JsonObject();
							jo.addProperty("code", 4);
							jo.addProperty("errmsg","您没有足够的权限!");
							SessionMethod.writeresp(response, g.toJson(jo));
							return false;
						}
						SessionMethod.sendRedirect(response, request, ConstantVar.authorizationHint);
						return false;
					}
				}else{
					return true;
				}
			}else{	
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return false;
	}
}
