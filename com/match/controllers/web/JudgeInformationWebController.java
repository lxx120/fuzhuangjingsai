package com.match.controllers.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.baciss.model.JudgeInformation;
import com.match.baciss.model.LoginMessage;
import com.match.baciss.service.JudgeInformationService;
import com.match.common.Jacksonmethod;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageResult;

@Controller
@RequestMapping(value = "/WebJudgeInformation")
public class JudgeInformationWebController {

	@Resource
	private JudgeInformationService JudgeInformationService;
	
	@RequestMapping(value = "/updateJudgeInformation.htm")
	@ResponseBody
	public String updateJudgeInformation(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			JudgeInformation judgeInformation = new Zhuanbianduixiang().reflects(r, JudgeInformation.class);
			boolean flag = JudgeInformationService.updateJudgeInformation(judgeInformation);
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
	
	
	@RequestMapping(value = "/addJudgeInformation1.htm")
	@ResponseBody
	public String addJudgeInformation1(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			JudgeInformation judgeInformation = new Zhuanbianduixiang().reflects(r, JudgeInformation.class);
			judgeInformation.setUserid(getlogin.getId());
			int i  = JudgeInformationService.addJudgeInformation1(judgeInformation);
			if (i==1) {
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
	
	@RequestMapping(value = "/findJudgeInformationByUserid.htm")
	@ResponseBody
	public String findJudgeInformationByUserid(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>>  obb = new ObjectResult<Map<String,Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Map<String,Object> map = JudgeInformationService.findJudgeInformationByUserid(getlogin.getId());
			obb.setCode(0);
			obb.setErrmsg("查询成功");
			obb.setItem(map);
		} else {
			obb.setCode(1);
			obb.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(obb, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
}
