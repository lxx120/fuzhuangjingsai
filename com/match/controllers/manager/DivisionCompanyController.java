package com.match.controllers.manager;

import java.util.List;
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
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.model.DivisionCompany;
import com.match.proposition.service.DivisionCompanyService;

@Controller
@RequestMapping(value = "/DivisionCompany")
public class DivisionCompanyController {

	@Autowired
	private  DivisionCompanyService  DivisionCompanyService;
	
	@RequestMapping(value = "/findOthersCompanyPage.htm")
	@ResponseBody
	public String findOthersCompanyPage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			String province = RequestParam.getSqlString(r, "province");
			String city = RequestParam.getSqlString(r, "city");
			String name = RequestParam.getSqlString(r, "name");
			int type = RequestParam.getInt(r, "type");
			if(type==1)
			{
				result = DivisionCompanyService.findOthersCompanyPage(province, city, getlogin.getDivisionid(), name,
						pageModel);
			}
			else if (type==2)
			{
				result = DivisionCompanyService.findBangDingCompany(province, city, getlogin.getDivisionid(), name,
						pageModel);
			}
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

	@RequestMapping(value = "/findBangDingCompany.htm")
	@ResponseBody
	public String findBangDingCompany(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			String province = RequestParam.getSqlString(r, "province");
			String city = RequestParam.getSqlString(r, "city");
			String name = RequestParam.getSqlString(r, "name");
			result = DivisionCompanyService.findBangDingCompany(province, city, getlogin.getDivisionid(), name,
					pageModel);
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

	@RequestMapping(value = "/deleteDivisionCompany.htm")
	@ResponseBody
	public String deleteDivisionCompany(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			if (0 == id) {
				result.setCode(1);
				result.setErrmsg("id不能为空");
			} else {
				boolean flag = DivisionCompanyService.deleteDivisionCompany(id);
				if (flag) {
					result.setCode(0);
					result.setErrmsg("删除成功");
				} else {
					result.setCode(1);
					result.setErrmsg("删除失败");
				}
			}

		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}

	@RequestMapping(value = "/addBatchDivisionCompany.htm")
	@ResponseBody
	public String addBatchDivisionCompany(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			List<Map<String, Object>> list = gson.fromJson(s, List.class);
			int i = DivisionCompanyService.addBatchDivisionCompany(getlogin.getDivisionid(), list);
			if (i == 1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} else if (i == -1) {
				result.setCode(1);
				result.setErrmsg("添加失败");
			} else {
				result.setCode(1);
				result.setErrmsg("暂无进行的比赛");
			}

		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/addDivisionCompany.htm")
	@ResponseBody
	public String addDivisionCompany(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			DivisionCompany deDivisionCompany = new Zhuanbianduixiang().reflects(r, DivisionCompany.class);
			int i = DivisionCompanyService.addDivisionCompany(getlogin.getCompetitionid(),getlogin.getDivisionid(), deDivisionCompany);
			if (i == 1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} else if (i == -1) {
				result.setCode(1);
				result.setErrmsg("添加失败");
			} else if(i==2)
			{
				result.setCode(1);
				result.setErrmsg("已经绑定过");
			}
			else if(i==3)
			{
				result.setCode(1);
				result.setErrmsg("其它赛区已经绑定过");
			}
			else {
				result.setCode(1);
				result.setErrmsg("暂无进行的比赛");
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
