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
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.model.Intermatch;
import com.match.proposition.model.Intermatchperson;
import com.match.proposition.service.IntermatchSerice;

@Controller
@RequestMapping(value = "/WebIntermatch")
public class IntermatchSericeWebController {

	@Autowired
	private  IntermatchSerice  IntermatchSerice;
	
	@RequestMapping(value = "/addIntermatch.htm")
	@ResponseBody
	public String addIntermatch(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Intermatch intermatch = new Zhuanbianduixiang().reflects(r, Intermatch.class);
			int i  = IntermatchSerice.addIntermatch(intermatch);
			if (i==1) {
				orr.setCode(0);
				orr.setErrmsg("添加成功");
			} 
			else if(i==2)
			{
				orr.setCode(1);
				orr.setErrmsg("已经获过奖，不能参与网络比赛");
			}
			else if(i==3)
			{
				orr.setCode(1);
				orr.setErrmsg("网络复活赛还未开始");
			}
			else if(i==4)
			{
				orr.setCode(1);
				orr.setErrmsg("网络复活赛已经结束");
			}
			else if(i==5)
			{
				orr.setCode(1);
				orr.setErrmsg("暂无比赛");
			}
			else if(i==6)
			{
				orr.setCode(1);
				orr.setErrmsg("此作品已参加网络比赛");
			}
			else {
				orr.setCode(1);
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
	
	
	@RequestMapping(value = "/updateIntermatch.htm")
	@ResponseBody
	public String updateIntermatch(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Intermatchperson intermatchperson = new Zhuanbianduixiang().reflects(r, Intermatchperson.class);
			intermatchperson.setCompetitionid(getlogin.getCompetitionid());
			intermatchperson.setPersonid(getlogin.getId());
			int i  = IntermatchSerice.updateIntermatch(intermatchperson);
			if (i==1) {
				orr.setCode(0);
				orr.setErrmsg("投票成功");
			} 
			else if(i==2)
			{
				orr.setCode(1);
				orr.setErrmsg("您已投过票");
			}
			
			else {
				orr.setCode(1);
				orr.setErrmsg("投票失败");
			}
		} else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法投票");
		}
		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}
	
	
	@RequestMapping(value = "/findIntermatch.htm")
	@ResponseBody
	public String findIntermatch(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
//		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			String code = RequestParam.getString(r, "code");
			long enterpriseid = RequestParam.getLong(r, "enterpriseid");
			result = IntermatchSerice.findIntermatch(code, enterpriseid, pageModel);
			if (result!=null&&result.getItems() != null && result.getItems().size() > 0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
			} else {
				result.setCode(1);
				result.setErrmsg("暂无数据");
			}
//		} else {
//			result.setCode(1);
//			result.setErrmsg("尚未登录，无法获取数据");
//		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
}
