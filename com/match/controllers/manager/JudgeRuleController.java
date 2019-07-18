package com.match.controllers.manager;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.match.baciss.model.LoginMessage;
import com.match.common.Jacksonmethod;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageResult;
import com.match.judges.model.Judge;
import com.match.judges.model.JudgeRule;
import com.match.judges.service.JudgeRuleService;

@Controller
@RequestMapping(value = "/JudgeRule")
public class JudgeRuleController {

	@Resource
	private JudgeRuleService JudgeRuleService;

	@RequestMapping(value = "/addJudgeRule.htm")
	@ResponseBody
	public String addJudgeRule(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
//			JudgeRule judgeRule = new JudgeRule();
//			judgeRule  = RequestParam.getobj(r,judgeRule);
//			List<Map<String, Object>> list = judgeRule.getList();
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			List<Map<String,Object>> list = gson.fromJson(s, List.class);
			for (Map<String, Object> map : list) {
				map.put("competittionid", getlogin.getCompetitionid());
				map.put("divisionid", getlogin.getDivisionid());
			}
			boolean flag = JudgeRuleService.addJudgeRule(list);
			if (flag) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} else {
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

	@RequestMapping(value = "/deleteJudgeRule.htm")
	@ResponseBody
	public String deleteJudgeRule(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			boolean flag = JudgeRuleService.deleteJudgeRule(id);
			if (flag) {
				orr.setCode(0);
				orr.setErrmsg("删除成功");
			} else {
				orr.setCode(1);
				orr.setErrmsg("删除失败");
			}
		} else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法获取数据");
		}
		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}

	@RequestMapping(value = "/findJudgeRule.htm")
	@ResponseBody
	public String findJudgeRule(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "arid");
			List<Map<String, Object>> map = JudgeRuleService.findJudgeRuleList(id);
			if (map != null && map.size() > 0) {
				orr.setCode(0);
				orr.setItem(map);
				orr.setErrmsg("查询成功");
			} else {
				orr.setCode(1);
				orr.setErrmsg("暂无数据");
			}
		} else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法获取数据");
		}

		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}

	@RequestMapping(value = "/updateJudgeRule.htm")
	@ResponseBody
	public String updateJudgeRule(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			JudgeRule judgeRule = new Zhuanbianduixiang().reflects(r, JudgeRule.class);
			boolean flag = JudgeRuleService.updateJudgeRule(judgeRule);
			if (flag) {
				result.setCode(0);
				result.setErrmsg("修改成功");
			} else {
				result.setCode(1);
				result.setErrmsg("修改失败");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findJudgeRuleInfo.htm")
	@ResponseBody
	public String findJudgeRuleInfo(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			Map<String, Object> map = JudgeRuleService.findJudgeRuleInfo(id);
			if (map != null && map.size() > 0) {
				orr.setCode(0);
				orr.setItem(map);
				orr.setErrmsg("查询成功");
			} else {
				orr.setCode(1);
				orr.setErrmsg("暂无数据");
			}
		} else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法获取数据");
		}

		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}
}
