package com.match.springmvc.interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class WechatLoginInterceptor extends HandlerInterceptorAdapter {
	
	
	private static final String authenCallback="/merchant/wechat/authen/callback.htm";
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		boolean isWechatBrowser=false;
//		Object iwb=request.getSession().getAttribute("isWechatBrowser");
//		if(iwb==null){
//			String userAgent = request.getHeader("user-agent").toLowerCase();
//			if(userAgent.indexOf("micromessenger")>-1){//微信客户端
//				isWechatBrowser=true;
//			}
//			request.getSession().setAttribute("isWechatBrowser", isWechatBrowser);
//		}else{
//			isWechatBrowser=(Boolean)iwb;
//		}
//		if(!isWechatBrowser){
//			return true;
//		}
//		Wechat wechat=SessionMethod.getSessionAttribute(request, ConstantVar.session_wechat_name, Wechat.class);
//		if(wechat!=null)
//			return true;
//		wechat=this.wechatPool.get(request.getSession().getId());
//		if(wechat!=null){
//			request.getSession().setAttribute(ConstantVar.session_wechat_name, wechat);
//			return true;
//		}
////		Map<String, String> stateMap=new HashMap<String, String>();
////		stateMap.put("callback_redirect_url", URLEncoder.encode(request.getRequestURL().toString(), "utf-8"));
////		stateMap.put("sess", request.getSession().getId());
//		String state=request.getSession().getId();//Jacksonmethod.tojson(stateMap, false);
//		String authenURL=WechatAuthenUtils.getAuthenCodeUrl(WechatAuthenUtils.Scope.snsapi_base,ConstantVar.web_domain+WechatLoginInterceptor.authenCallback,state);
//		response.sendRedirect(authenURL);
		return false;
	}
}
