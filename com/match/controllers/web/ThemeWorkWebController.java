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
import com.match.baciss.model.User;
import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.model.ChaXunTiaoJian1;
import com.match.proposition.model.ThemeSignup;
import com.match.proposition.model.ThemeWork;
import com.match.proposition.service.ThemeWorkService;

@Controller
@RequestMapping(value = "/WebThemeWork")
public class ThemeWorkWebController {

	@Autowired
	private  ThemeWorkService  ThemeWorkService;
	
	@RequestMapping(value = "/addThemeWork.htm")
	@ResponseBody
	public String addThemeWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			ThemeWork themeWork = gson.fromJson(s,ThemeWork.class);
			themeWork.setCompetitionid(getlogin.getCompetitionid());
			themeWork.setDivisionid(getlogin.getDivisionid());
			themeWork.setCollegeid(getlogin.getEnterpriseid());
			int i = ThemeWorkService.addThemeWork(themeWork);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} 
			else if(i==2)
			{
				result.setCode(1);
				result.setErrmsg("还未到作品提交时间");
			}
			else if(i==3)
			{
				result.setCode(1);
				result.setErrmsg("已过提交作品截止时间");
			}
			else if(i==4)
			{
				result.setCode(1);
				result.setErrmsg("还没有轮次");
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
	
	
	@RequestMapping(value = "/updateThemeWork.htm")
	@ResponseBody
	public String updateThemeWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			ThemeWork themeWork = gson.fromJson(s,ThemeWork.class);	
			int i = ThemeWorkService.updateThemeWork(themeWork);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("修改成功");
			} 
			else if(i==2)
			{
				result.setCode(1);
				result.setErrmsg("还未到作品提交时间,不能修改");
			}
			else if(i==3)
			{
				result.setCode(1);
				result.setErrmsg("已过提交作品截止时间，不能修改");
			}
			else  {
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
	
	
	@RequestMapping(value = "/findMyThemeWork.htm")
	@ResponseBody
	public String findMyThemeWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
//		if (getlogin != null) {
			ThemeWork themeWork = new Zhuanbianduixiang().reflects(r, ThemeWork.class);
			long themesignupid = RequestParam.getLong(r, "themesignupid");
			Map<String,Object> map = ThemeWorkService.findMyThemeWork(themesignupid);
			if (map!=null) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(map);
			} 
			else  {
				result.setCode(1);
				result.setErrmsg("查询失败");
			}
//		} else {
//			result.setCode(1);
//			result.setErrmsg("尚未登录，无法获取数据");
//		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findThemeWorkByOwner.htm")
	@ResponseBody
	public String findThemeWorkByOwner(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			long competitionid = RequestParam.getLong(r, "competitionid");
			long enterpriseid = RequestParam.getLong(r, "enterpriseid");
			result = ThemeWorkService.findThemeWorkByOwner(getlogin.getId(), competitionid, enterpriseid, pageModel);
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
	
	
	@RequestMapping(value = "/findMyWork.htm")
	@ResponseBody
	public String findMyWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			ChaXunTiaoJian1 chaXunTiaoJian1 = new Zhuanbianduixiang().reflects(r,ChaXunTiaoJian1.class);
			result = ThemeWorkService.findMyWork(getlogin.getId(),chaXunTiaoJian1, pageModel);
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
	
	
}
