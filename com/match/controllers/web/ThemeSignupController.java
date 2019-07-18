package com.match.controllers.web;

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
import com.match.proposition.model.ThemeSignup;
import com.match.proposition.service.ThemeSignupService;

@Controller
@RequestMapping(value = "/WebThemeSignup")
public class ThemeSignupController {

	@Resource
	private  ThemeSignupService  ThemeSignupService;
	
	@RequestMapping(value = "/addThemeSignup.htm")
	@ResponseBody
	public String addThemeSignup(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			ThemeSignup themeSignup = gson.fromJson(s,ThemeSignup.class);
			themeSignup.setUserid(getlogin.getId());
			themeSignup.setCompetitionid(getlogin.getCompetitionid());
			themeSignup.setDivisionid(getlogin.getDivisionid());
			themeSignup.setRealname(getlogin.getRealname());
			themeSignup.setPhone(getlogin.getPhone());
			int i = ThemeSignupService.addThemeSignup(themeSignup);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} 
			else if(i==5)
			{
				result.setCode(1);
				result.setErrmsg("还未到报名时间时间");
			}
			else if(i==6)
			{
				result.setCode(1);
				result.setErrmsg("报名时间已过");
			}
			else if(i==7)
			{
				result.setCode(1);
				result.setErrmsg("个人参赛只能一人参加");
			}
			else if(i==0) {
				result.setCode(1);
				result.setErrmsg("团对参赛要填写多人信息");
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
	
	
	@RequestMapping(value = "/findMyThemeSignup.htm")
	@ResponseBody
	public String findMyThemeSignup(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String,Object>>> result = new ObjectResult<>();		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			ThemeSignup themeSignup = new Zhuanbianduixiang().reflects(r, ThemeSignup.class);
			themeSignup.setUserid(getlogin.getId());
			long competitionid = RequestParam.getLong(r, "competitionid");
			String name = RequestParam.getString(r, "name");
			List<Map<String,Object>> list = ThemeSignupService.findMyThemeSignup(getlogin.getId(), competitionid, name);
			if (list!=null && list.size()>0) {
				result.setCode(0);
				result.setErrmsg("添加成功");
				result.setItem(list);
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
	
	
	@RequestMapping(value = "/updateThemeSignup.htm")
	@ResponseBody
	public String updateThemeSignup(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>> result = new ObjectResult<>();		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			ThemeSignup themeSignup = gson.fromJson(s,ThemeSignup.class);
			themeSignup.setUserid(getlogin.getId());
			int i = ThemeSignupService.updateThemeSignup(themeSignup);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("修改成功");
			} 
			else if(i==5)
			{
				result.setCode(1);
				result.setErrmsg("还未到报名时间时间");
			}
			else if(i==6)
			{
				result.setCode(1);
				result.setErrmsg("报名时间已过");
			}
			else if(i==7)
			{
				result.setCode(1);
				result.setErrmsg("个人参赛只能一人参加");
			}
			else if(i==0) {
				result.setCode(1);
				result.setErrmsg("团对参赛要填写多人信息");
			}
			else  {
				result.setCode(1);
				result.setErrmsg("失败");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findThemeSignupInfo.htm")
	@ResponseBody
	public String findThemeSignupInfo(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			Map<String,Object> map = ThemeSignupService.findThemeSignupInfo(id);
			if (map!=null) {
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
	
	
	@RequestMapping(value = "/findInfoByThemesignId.htm")
	@ResponseBody
	public String findInfoByThemesignId(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			Map<String,Object> map = ThemeSignupService.findInfoByThemesignId(id);
			if (map!=null) {
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
