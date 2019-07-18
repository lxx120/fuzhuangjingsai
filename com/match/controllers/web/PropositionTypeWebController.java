package com.match.controllers.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.baciss.model.LoginMessage;
import com.match.common.Jacksonmethod;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.proposition.service.PropositionTypeService;

@Controller
@RequestMapping(value = "/WebPropositionType")
public class PropositionTypeWebController {

	@Resource
	private  PropositionTypeService  PropositionTypeService;
	
	@RequestMapping(value = "/findPropositionType.htm")
	@ResponseBody
	public String findPropositionType(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
//		if (getlogin != null) {
		List<Map<String, Object>> lsit = PropositionTypeService.findPropositionType();
		orr.setCode(0);
		orr.setItem(lsit);
		orr.setErrmsg("查询成功");
//		} else {
//			orr.setCode(1);
//			orr.setErrmsg("尚未登录，无法获取数据");
//		}
		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}
}
