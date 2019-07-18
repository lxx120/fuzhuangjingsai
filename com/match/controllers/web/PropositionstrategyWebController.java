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
import com.match.common.Jacksonmethod;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.org.model.propositionstrategy;
import com.match.org.service.PropositionstrategyService;

@Controller
@RequestMapping(value = "/WebPropositionstrategy")
public class PropositionstrategyWebController {

	@Autowired
	private PropositionstrategyService PropositionstrategyService;

	@RequestMapping(value = "/addPropositionstrategy.htm")
	@ResponseBody
	public String addPropositionstrategy(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		propositionstrategy propositionstrategy = new Zhuanbianduixiang().reflects(r, propositionstrategy.class);
		if (getlogin != null) {
			propositionstrategy.setEnterpriseid(getlogin.getEnterpriseid());
			propositionstrategy.setCompetitionid(getlogin.getCompetitionid());
			boolean flag = PropositionstrategyService.addPropositionstrategy(propositionstrategy);
			if (flag) {
				orr.setCode(0);
				orr.setErrmsg("添加成功");
			} else {
				orr.setCode(0);
				orr.setErrmsg("添加失败");
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

	@RequestMapping(value = "/updatePropositionstrategy.htm")
	@ResponseBody
	public String updatePropositionstrategy(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		propositionstrategy propositionstrategy = new Zhuanbianduixiang().reflects(r, propositionstrategy.class);
		if (getlogin != null) {
			boolean flag = PropositionstrategyService.updatePropositionstrategy(propositionstrategy);
			if (flag) {
				orr.setCode(0);
				orr.setErrmsg("修改成功");
			} else {
				orr.setCode(0);
				orr.setErrmsg("修改失败");
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

	@RequestMapping(value = "/findPropositionstrategy.htm")
	@ResponseBody
	public String findPropositionstrategy(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id  =  RequestParam.getLong(r, "id");
			Map<String, Object> map = PropositionstrategyService.findPropositionstrategy(id);
			if (map != null) {
				orr.setCode(0);
				orr.setItem(map);
				orr.setErrmsg("查询成功");
			} else {
				orr.setCode(0);
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
