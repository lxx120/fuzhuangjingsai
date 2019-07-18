/*
 * package com.match.springmvc.interceptors;
 * 
 * import java.util.Date;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.springframework.web.method.HandlerMethod; import
 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 * 
 * import com.match.common.ConstantVar; import com.match.common.SessionMethod;
 * import com.match.springutils.WebLogger; import
 * com.match.systemconfig.persist.ActionLog; import
 * com.match.systemconfig.persist.Login;
 * 
 * public class LogInterceptor extends HandlerInterceptorAdapter{
 * 
 * @Override public void afterCompletion(HttpServletRequest request,
 * HttpServletResponse response, Object handler, Exception ex) throws Exception
 * { try{ if (handler instanceof HandlerMethod) { HandlerMethod methodHandler =
 * (HandlerMethod) handler; WebLogger logger =
 * methodHandler.getMethod().getAnnotation(WebLogger.class); if (logger != null)
 * { ActionLog alog = new ActionLog(); alog=SessionMethod.getActionLog(request,
 * alog); alog.setModule(logger.module());
 * alog.setOperateIP(request.getRemoteAddr()); alog.setOtime(new Date());
 * alog.setTitle(logger.title()); Login
 * login=(Login)request.getSession().getAttribute(ConstantVar.sessionLoginName);
 * if(login!=null){ alog.setOperator(login.getUsername());
 * alog.setSubject(login.getMerchant()+""); } //to save } } }catch(Exception e){
 * e.printStackTrace(); }finally{
 * 
 * } }
 * 
 * }
 */