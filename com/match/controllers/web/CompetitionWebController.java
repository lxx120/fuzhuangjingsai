package com.match.controllers.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.baciss.model.LoginMessage;
import com.match.baciss.model.Teacher;
import com.match.baciss.model.User;
import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.service.CompetitionService;

@Controller
@RequestMapping(value = "/WebCompetition")
public class CompetitionWebController {

	@Autowired
	private CompetitionService CompetitionService;

	@RequestMapping(value = "/fidnAllCompetition.htm")
	@ResponseBody
	public String fidnAllCompetition(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
//		if (getlogin != null) {
		List<Map<String, Object>> lsit = CompetitionService.fidnAllCompetition();
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

	@RequestMapping(value = "/findRewardedList.htm")
	@ResponseBody
	public String findRewardedList(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		PageModel pageModel = new Pageparam().getPageParam(r);
		result = CompetitionService.findRewardedList(pageModel);
		if (result != null) {
			result.setCode(0);
			result.setErrmsg("查询成功");
		} else {
			result.setCode(1);
			result.setErrmsg("暂无数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	@RequestMapping(value = "/findRewardedListInfo.htm")
	@ResponseBody
	public String findRewardedListInfo(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		long id = RequestParam.getLong(r, "id");
		Map<String,Object> list = CompetitionService.findfindRewardedListInfo(id);
			if (list != null) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(list);
			} else {
				result.setCode(1);
				result.setErrmsg("暂无数据");
			}
		String str = "";
		str = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}

	@RequestMapping(value = "/findSchoolRewardedList.htm")
	@ResponseBody
	public String findSchoolRewardedList(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			List<Map<String,Object>> list = CompetitionService.findSchoolRewardedList(getlogin.getEnterpriseid(),getlogin.getCompetitionid(), getlogin.getDivisionid());
			if (list != null) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(list);
			} else {
				result.setCode(1);
				result.setErrmsg("暂无数据");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}
		String str = "";
		str = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}
	
	
	@RequestMapping(value = "/findSchoolRewardedListManager.htm")
	@ResponseBody
	public String findSchoolRewardedListManager(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long enterpriseid = RequestParam.getLong(r, "enterpriseid");
			List<Map<String,Object>> list = CompetitionService.findSchoolRewardedList(enterpriseid,getlogin.getCompetitionid(), getlogin.getDivisionid());
			if (list != null) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(list);
			} else {
				result.setCode(1);
				result.setErrmsg("暂无数据");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}
		String str = "";
		str = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}

}
