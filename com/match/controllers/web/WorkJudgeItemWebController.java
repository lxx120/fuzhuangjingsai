package com.match.controllers.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.match.baciss.model.LoginMessage;
import com.match.common.Jacksonmethod;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.PageResult;
import com.match.judges.model.WorkJudgeItem;
import com.match.judges.service.WorkJudgeItemService;
import com.match.proposition.model.Competition1;

@Controller
@RequestMapping(value = "/WorkJudgeItem")
public class WorkJudgeItemWebController {

	@Autowired
	private  WorkJudgeItemService  WorkJudgeItemService;
	
	@RequestMapping(value = "/addWorkJudgeItem.htm")
	@ResponseBody
	public String addWorkJudgeItem(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			WorkJudgeItem workJudgeItem = gson.fromJson(s, WorkJudgeItem.class);
			int i = WorkJudgeItemService.addWorkJudgeItem(workJudgeItem, workJudgeItem.getType());
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} 
			else if(i==2) {
				result.setCode(1);
				result.setErrmsg("打分项为空不能添加");
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
