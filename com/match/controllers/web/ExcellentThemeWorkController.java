package com.match.controllers.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.match.baciss.model.JudgeInformation;
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
import com.match.reviewresult.model.ExcellentThemeWork;
import com.match.reviewresult.service.ExcellentThemeWorkService;

@Controller
@RequestMapping(value = "/ExcellentThemeWork")
public class ExcellentThemeWorkController {

	@Autowired
	private  ExcellentThemeWorkService  ExcellentThemeWorkService;
	
	@RequestMapping(value = "/addBatchExcellentThemeWork.htm")
	@ResponseBody
	public String addBatchExcellentThemeWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			List<Map<String,Object>> list = gson.fromJson(s, List.class);
			int i  = ExcellentThemeWorkService.addBatchExcellentThemeWork(getlogin.getCompetitionid(), list);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} 
			else if(i==2)
			{
				result.setCode(1);
				result.setErrmsg("请先配置个数");
			}
			else if(i==3)
			{
				result.setCode(1);
				result.setErrmsg("优秀作品数量超过上限");
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
	
	
	@RequestMapping(value = "/deleteExcellentThemeWork.htm")
	@ResponseBody
	public String deleteExcellentThemeWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			int i  = ExcellentThemeWorkService.deleteExcellentThemeWork(id);
			if (i>0) {
				result.setCode(0);
				result.setErrmsg("删除成功");
			} 
			else {
				result.setCode(1);
				result.setErrmsg("删除失败");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findExcellentThemeWork.htm")
	@ResponseBody
	public String findExcellentThemeWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> result = new ObjectResult<>();
			List<Map<String,Object>> list  = ExcellentThemeWorkService.findExcellentThemeWork();
			if (list!=null && list.size()>0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(list);
			} 
			else {
				result.setCode(1);
				result.setErrmsg("暂无数据");
			}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findOtherThemeWork.htm")
	@ResponseBody
	public String findOtherThemeWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			String stwcode = RequestParam.getString(r, "stwcode");
			String code = RequestParam.getString(r, "code");
			String phone = RequestParam.getString(r, "phone");
			result = ExcellentThemeWorkService.findOtherThemeWork(getlogin.getCompetitionid(), stwcode, code, phone, pageModel);
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
