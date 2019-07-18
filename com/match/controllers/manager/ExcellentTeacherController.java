package com.match.controllers.manager;

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
import com.match.reviewresult.model.ExcellentTeacher;
import com.match.reviewresult.service.ExcellentTeacherService;

@Controller
@RequestMapping(value = "/ExcellentTeache")
public class ExcellentTeacherController {

	@Autowired
	private  ExcellentTeacherService  ExcellentTeacherService;
	
	@RequestMapping(value = "/addExcellentTeacher.htm")
	@ResponseBody
	public String addExcellentTeacher(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			ExcellentTeacher  excellentTeacher = new Zhuanbianduixiang().reflects(r, ExcellentTeacher.class);
			excellentTeacher.setCompetationid(getlogin.getCompetitionid());
			excellentTeacher.setDivisionid(getlogin.getDivisionid());
			int i  = ExcellentTeacherService.addExcellentTeacher(excellentTeacher);
			if (i>0) {
				orr.setCode(0);
				orr.setErrmsg("添加成功");
			}
			else if(i==-1)
			{
				orr.setCode(1);
				orr.setErrmsg("请先配置优秀教师人数");
			}
			else if(i==-2)
			{
				orr.setCode(1);
				orr.setErrmsg("优秀教师人数已达上限");
			}
			else {
				orr.setCode(1);
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
	
	
	@RequestMapping(value = "/deleteExcellentTeacher.htm")
	@ResponseBody
	public String deleteExcellentTeacher(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			int i = ExcellentTeacherService.deleteExcellentTeacher(id);
			if (i>0) {
				orr.setCode(0);
				orr.setErrmsg("删除成功");
			} else {
				orr.setCode(1);
				orr.setErrmsg("删除失败");
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
	
	
	@RequestMapping(value = "/findExcellentTeacherPage.htm")
	@ResponseBody
	public String findExcellentTeacherPage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			result = ExcellentTeacherService.findExcellentTeacherPage(pageModel);
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
	
	
	@RequestMapping(value = "/findAllTeacherScorePage.htm")
	@ResponseBody
	public String findAllTeacherScorePage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			long collegeid = RequestParam.getLong(r, "collegeid");
			result = ExcellentTeacherService.findAllTeacherScorePage(getlogin.getCompetitionid(),collegeid,pageModel);
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
