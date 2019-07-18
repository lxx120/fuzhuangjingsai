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
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.judges.service.WorkJudgeService;

@Controller
@RequestMapping(value = "/WorkJudge")
public class WorkJudgeController {
	
	@Autowired
	private  WorkJudgeService  WorkJudgeService;
	
	@RequestMapping(value = "/findJudgeWork.htm")
	@ResponseBody
	public String findJudgeWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			int  status  =  RequestParam.getInt(r, "status");
			result = WorkJudgeService.findJudgeWork(getlogin.getJudgeid(), status, pageModel);
			if (result.getItems() != null && result.getItems().size() > 0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
			} else {
				result.setCode(1);
				result.setErrmsg("暂无数据");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}

	
	@RequestMapping(value = "/findWorkRuleInformation.htm")
	@ResponseBody
	public String findWorkRuleInformation(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>> result = new ObjectResult<>();		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			Map<String,Object> map = WorkJudgeService.findWorkRuleInformation(id);
			if (map!=null ) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(map);
			} 
			else  {
				result.setCode(1);
				result.setErrmsg("暂无数据");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findWorkRuleInformationManager.htm")
	@ResponseBody
	public String findWorkRuleInformationManager(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>> result = new ObjectResult<>();		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			Map<String,Object> map = WorkJudgeService.findWorkRuleInformationManager(id);
			if (map!=null ) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(map);
			} 
			else  {
				result.setCode(1);
				result.setErrmsg("暂无数据");
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
