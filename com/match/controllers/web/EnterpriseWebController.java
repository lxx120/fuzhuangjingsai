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
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.org.model.Enterprise;
import com.match.org.service.EnterpriseService;

@Controller
@RequestMapping(value = "/WebEnterprise")
public class EnterpriseWebController {

	@Resource
	private EnterpriseService enterpriseService;
	
	@RequestMapping(value = "/findEnterpriseByIdWeb.htm")
	@ResponseBody
	public String findEnterpriseById(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
				Map<String, Object> map = enterpriseService.findEnterpriseByIdWeb(getlogin.getEnterpriseid());
				orr.setCode(0);
				orr.setItem(map);
				orr.setErrmsg("查询成功");
		} else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法获取数据");
		}
		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}
	
	
	@RequestMapping(value = "/updateEnterpriseWeb.htm")
	@ResponseBody
	public String updateEnterprise(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Enterprise enterprise = new Zhuanbianduixiang().reflects(r, Enterprise.class);
			int i = enterpriseService.updateEnterpriseWeb(enterprise);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("修改成功");
			}
			else {
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
	
	
	
	@RequestMapping(value = "/findEnterpriseJoinComThemeWork.htm")
	@ResponseBody
	public String findEnterpriseJoinComThemeWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		PageModel pageModel = new Pageparam().getPageParam(r);
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			result = enterpriseService.findEnterpriseJoinComThemeWork(getlogin.getEnterpriseid(), id, pageModel);
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
	
	
	
	@RequestMapping(value = "/findEnterpriseByCompetitionid.htm")
	@ResponseBody
	public String findEnterpriseByCompetitionid(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long competitionid = RequestParam.getLong(r, "competitionid");
			List<Map<String,Object>>  list = enterpriseService.findEnterpriseByCompetitionid(competitionid);
			if (list!=null && list.size()>0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItems(list);
			}
			else {
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
	
	
	@RequestMapping(value = "/findEnterpriseByCompetitionid1.htm")
	@ResponseBody
	public String findEnterpriseByCompetitionid1(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
			List<Map<String,Object>>  list = enterpriseService.findEnterpriseByCompetitionid((long)0);
			if (list!=null && list.size()>0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItems(list);
			}
			else {
				result.setCode(1);
				result.setErrmsg("暂无数据");
			}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
}
