package com.match.springmvc.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.match.authors.ILoginService;
import com.match.common.BasicMethod;
import com.match.common.ConstantVar;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.systemconfig.persist.Login;

/**
 * 登录拦截器
 * @author mengly
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private Logger log=LogManager.getLogger();
	@Reference
	private ILoginService loginSer;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean isAjax=false;
		/**
		 * 如果用react的话 ，就全部是ajax，不需要判断
		 */
		if("XMLHttpRequest".equals(request.getHeader("X-Requested-With")))
			isAjax=true;
		
		HttpSession session=request.getSession();
		Object obj=session.getAttribute(ConstantVar.sessionLoginName);
		//login, 已经登录
		if(obj!=null)
			return true;
		//////////////////////////////测试用,要删除////////////////////////////
//		Login l=new Login();
//		l.setUserid(79);
//		l.setMerchant(60);
//		l.setMerchantName("小小商户");
//		session.setAttribute(ConstantVar.sessionLoginName, l);
//		session.setAttribute(ConstantVar.session_user_canbuy_flag_name, true);
//		session.setAttribute(ConstantVar.session_user_cansell_flag_name, true);
//		return true;

		if(isAjax){
			Gson g=new Gson();
			JsonObject jo=new JsonObject();
			jo.addProperty("code", 3);
			jo.addProperty("hint","您没有登录,请您登录!");
			SessionMethod.writeresp(response, g.toJson(jo));
			return false;
		}
		
		//login 没有登录
		Cookie[] cookies=request.getCookies();
		String login_token=null;
		for (Cookie cookie : cookies) {
			if(ConstantVar.loginTokenName.equals(cookie.getName())){
				login_token=cookie.getValue();
				break;
			}
		}
		log.info("#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$not login,start to login");
		String uri= request.getRequestURI();
		//需要登录
		if(login_token==null){
			log.info("#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$跳转登录页面");
			session.setAttribute(ConstantVar.sessionRedirectURLName, uri);
			response.sendRedirect(ConstantVar.beginLoginURL);
			return false;
		}
		login_token=BasicMethod.sqlformat(login_token);//防止sql等注入
		ObjectResult<Login> result=loginSer.checkLoginByToken(login_token);//判断以前是否登录
		if(result.getCode()==0){
			//隐藏登录成功
			log.info("#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$隐藏登录成功");
			session.setAttribute(ConstantVar.sessionLoginName, result.getItem());
			return true;
		}else{
			session.setAttribute(ConstantVar.sessionRedirectURLName, uri);
			//通过token登录没有成功
			log.info("#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$隐藏登录没有成功");
			response.sendRedirect(ConstantVar.beginLoginURL);
			return false;
		}
	}
	
}
