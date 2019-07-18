package com.match.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.match.common.ConstantVar;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;

public class DuplicationSubmitInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String session_duplication_code=request.getSession().getAttribute(ConstantVar.session_duplication_code_name)+"";
		SessionMethod.genDuplicationCode(request.getSession());//再次生成 code
		
		String client_duplication_code=RequestParam.getSqlString(request, ConstantVar.client_duplication_code_name);
		if(StringUtils.isBlank(client_duplication_code))
			return true;
		if(!client_duplication_code.equals(session_duplication_code))
			return false;
		return true;
	}
	
}
