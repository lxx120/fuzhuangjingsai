package com.match.controllers.manager;

import java.util.List;
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
import com.match.common.result.ObjectResult;
import com.match.judges.model.AssessGroup;
import com.match.judges.service.AssessGroupService;

@Controller
@RequestMapping(value = "/AssessGroup")
public class AssessGroupController {

	@Autowired
	private AssessGroupService AssessGroupService;

	@RequestMapping(value = "/findAssessGroup.htm")
	@ResponseBody
	public String findAssessGroup(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			AssessGroup assessGroup = new Zhuanbianduixiang().reflects(r, AssessGroup.class);
			List<Map<String, Object>> list = AssessGroupService.findAssessGroupList(assessGroup.getArrid());
			if (list != null && list.size() > 0) {
				orr.setCode(0);
				orr.setItem(list);
				orr.setErrmsg("查询成功");
			} else {
				orr.setCode(1);
				orr.setErrmsg("当前暂无比赛或者进行的轮次");
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
