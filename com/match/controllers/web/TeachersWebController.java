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
import com.match.baciss.model.LoginMessage;
import com.match.baciss.model.Teacher;
import com.match.baciss.service.TeacherService;
import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.service.TeachersService;

@Controller
@RequestMapping(value = "/WebTeacher")
public class TeachersWebController {

	@Autowired
	private TeachersService teachersService;
	
	@Autowired
	private TeacherService  teachersServiceba;

	@RequestMapping(value = "/findGoodTeacherPage.htm")
	@ResponseBody
	public String findGoodTeacherPage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		PageModel pageModel = new Pageparam().getPageParam(r);
		Teacher teacher = new Zhuanbianduixiang().reflects(r, Teacher.class);
		result = teachersService.findGoodTeacherPage(teacher, pageModel);
		if (result.getItems() != null && result.getItems().size() > 0) {
			result.setCode(0);
			result.setErrmsg("存在优秀教师");

		} else {
			result.setCode(1);
			result.setErrmsg("暂无数据");
		}
		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;

	}

	@RequestMapping(value = "/findTeacherTeam.htm")
	@ResponseBody
	public String findTeacherTeam(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			result = teachersService.findTeacherTeam(getlogin.getId(), getlogin.getCompetitionid(), pageModel);
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
	
	
	@RequestMapping(value = "/findTeamBuSignID.htm")
	@ResponseBody
	public String findTeamBuSignID(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			List<Map<String,Object>>  list  = teachersService.findTeamBuSignID(id);
			if (list != null && list.size() > 0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(list);
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
	
	
	@RequestMapping(value = "/updateTeamBuSign.htm")
	@ResponseBody
	public String updateTeamBuSign(HttpServletRequest r,HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			List<Map<String, Object>> list = gson.fromJson(s, List.class);
			int i   = teachersService.updateTeamBuSign(list);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("修改成功");
			} else {
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
	
	
	@RequestMapping(value = "/findTeacherScoreList.htm")
	@ResponseBody
	public String findTeacherScoreList(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			long prizeid = RequestParam.getLong(r, "prizeid");
			result = teachersServiceba.findTeacherScoreList(prizeid, getlogin.getCompetitionid(), getlogin.getPhone(), pageModel);
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
