package com.match.controllers.manager;

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
import com.match.org.model.College;
import com.match.org.service.CollegeService;

@Controller
@RequestMapping(value = "/College")
public class CollegeController {

	@Resource
	private CollegeService collegeservice;

	@RequestMapping(value = "/deleteCollege.htm")
	@ResponseBody
	public String deleteCollege(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		long id = RequestParam.getLong(r, "id");
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			int i = collegeservice.deleteCollege(id);
			if (i > 0) {
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

	@RequestMapping(value = "/findCollegeById.htm")
	@ResponseBody
	public String findCollegeById(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		long id = RequestParam.getLong(r, "id");
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			if (0 == id) {
				orr.setCode(1);
				orr.setErrmsg("院校id不能为空");
			} else {
				Map<String, Object> map = collegeservice.findCollegeById(id);
				orr.setCode(0);
				orr.setItem(map);
				orr.setErrmsg("查询成功");
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

	@RequestMapping(value = "/findCollegePage.htm")
	@ResponseBody
	public String findCollegePage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			College college = new Zhuanbianduixiang().reflects(r, College.class);
			result = collegeservice.findCollegePage(college, pageModel);
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

	@RequestMapping(value = "/updateCollege.htm")
	@ResponseBody
	public String updateCollege(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			College college = new Zhuanbianduixiang().reflects(r, College.class);
			long userid = RequestParam.getLong(r, "userid");
			int i = collegeservice.updateCollege(userid,college);
			if (i ==1 ) {
				result.setCode(0);
				result.setErrmsg("修改成功");
			} 
			else if(i==2)
			{
				result.setCode(1);
				result.setErrmsg("手机号已被注册");
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

	@RequestMapping(value = "/addCollege.htm")
	@ResponseBody
	public String addCollege(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String phone = RequestParam.getString(r, "phone");
			College college = new Zhuanbianduixiang().reflects(r, College.class);
			int i = collegeservice.addCollege(college,phone);
			if (i == 1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			}
			else if(i==2)
			{
				result.setCode(1);
				result.setErrmsg("手机号已经存在");
			}
			else {
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

	
	@RequestMapping(value = "/fingCollegeByDivision.htm")
	@ResponseBody
	public String fingCollegeByDivision(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			String name = RequestParam.getString(r, "name");
			result = collegeservice.fingCollegeByDivision(name,getlogin.getCompetitionid(),getlogin.getDivisionid(), pageModel);
			if (result!=null && result.getItems() != null && result.getItems().size() > 0) {
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
