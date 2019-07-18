package com.match.controllers.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.baciss.model.LoginMessage;
import com.match.common.Jacksonmethod;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.PageResult;
import com.match.proposition.model.CheatReport;
import com.match.proposition.service.CheatReportService;

@Controller
@RequestMapping(value = "/CheatReport")
public class CheatReportWebController {

	@Autowired
	private  CheatReportService  CheatReportService;
	
	@RequestMapping(value = "/addCheatReport.htm")
	@ResponseBody
	public String addCheatReport(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			CheatReport cheatReport = new Zhuanbianduixiang().reflects(r, CheatReport.class);
			cheatReport.setReporterid(getlogin.getId());
			int i = CheatReportService.addCheatReport(cheatReport);
			if (i>0) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} 
			else  {
				result.setCode(1);
				result.setErrmsg("添加失败");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
}
