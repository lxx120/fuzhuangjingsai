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
import com.match.org.model.College;
import com.match.org.service.CollegeService;

@Controller
@RequestMapping(value = "/WebCollege")
public class CollegeWebController {

	@Autowired
	private CollegeService CollegeService;

	/**
	 * 前台 名师名校 的展示
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findGoodCollegeListPage.htm")
	@ResponseBody
	public String findGoodCollegeListPage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		PageModel pageModel = new Pageparam().getPageParam(r);
		College college = new Zhuanbianduixiang().reflects(r, College.class);
		result = CollegeService.findGoodCollegeListPage(college, pageModel);
		if (result.getItems() != null && result.getItems().size() > 0) {
			result.setCode(0);
			result.setErrmsg("存在优秀组织院校");
		} else {
			result.setCode(1);
			result.setErrmsg("暂无数据");
		}
		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}

	@RequestMapping(value = "/findCollegeList.htm")
	@ResponseBody
	public String findCollegeList(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		String name = RequestParam.getString(r, "name");
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
//		if (getlogin != null) {
		List<Map<String, Object>> lsit = CollegeService.findCollegeList(name);
		orr.setCode(0);
		orr.setItem(lsit);
		orr.setErrmsg("查询成功");
//		} else {
//			orr.setCode(1);
//			orr.setErrmsg("尚未登录，无法获取数据");
//		}
		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}

	@RequestMapping(value = "/updateCollegeByID.htm")
	@ResponseBody
	public String updateCollegeByID(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			College college = new Zhuanbianduixiang().reflects(r, College.class);
			boolean flag = CollegeService.updateCollegeByID(college);
			if (flag) {
				orr.setCode(0);
				orr.setErrmsg("修改成功");
			} else {
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

	@RequestMapping(value = "/findCollegeById.htm")
	@ResponseBody
	public String findCollegeById(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = getlogin.getEnterpriseid();
			Map<String, Object> lsit = CollegeService.findCollegeById(id);
			if (lsit != null && lsit.size() > 0) {
				orr.setCode(0);
				orr.setItem(lsit);
				orr.setErrmsg("查询成功");
			} else {
				orr.setCode(1);
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
	
	
	@RequestMapping(value = "/findCollegeJoinCompetition.htm")
	@ResponseBody
	public String findCollegeJoinCompetition(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			result = CollegeService.findCollegeJoinCompetition(getlogin.getCompetitionid(),getlogin.getEnterpriseid(), pageModel);
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
	
	
	@RequestMapping(value = "/findCollegeTeacher.htm")
	@ResponseBody
	public String findCollegeTeacher(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			result = CollegeService.findCollegeTeacher(getlogin.getCompetitionid(),getlogin.getEnterpriseid(), pageModel);
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
	
	
	@RequestMapping(value = "/findCollegeStudent.htm")
	@ResponseBody
	public String findCollegeStudent(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			String jename = RequestParam.getString(r, "jename");
			String major = RequestParam.getString(r, "major");
			result = CollegeService.findCollegeStudent(jename,major,getlogin.getCompetitionid(),getlogin.getEnterpriseid(), pageModel);
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
