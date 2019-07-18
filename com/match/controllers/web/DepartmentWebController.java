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
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.org.model.Department;
import com.match.org.service.DepartmentService;

@Controller
@RequestMapping(value = "/WebDepartment")
public class DepartmentWebController {

	@Autowired
	private DepartmentService DepartmentService;

	@RequestMapping(value = "/findDepartmentList.htm")
	@ResponseBody
	public String findDepartmentList(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		Department department = new Zhuanbianduixiang().reflects(r, Department.class);
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if(department.getCollegeid()==null && getlogin!=null)
		{
			department.setCollegeid(getlogin.getEnterpriseid());
		}
//		if (getlogin != null) {
		List<Map<String, Object>> lsit = DepartmentService.findDepartmentList(department);
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

	@RequestMapping(value = "/addDepartment.htm")
	@ResponseBody
	public String addDepartment(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Department department = new Zhuanbianduixiang().reflects(r, Department.class);
			department.setCollegeid(getlogin.getEnterpriseid());
			int i = DepartmentService.addDepartment(department);
			if (i > 0) {
				orr.setCode(0);
				orr.setErrmsg("添加成功");
			} else {
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
	
	
	@RequestMapping(value = "/updateDepartment.htm")
	@ResponseBody
	public String updateDepartment(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Department department = new Zhuanbianduixiang().reflects(r, Department.class);
			int i = DepartmentService.updateDepartment(department);
			if (i > 0) {
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
	
	@RequestMapping(value = "/deleteDepartment.htm")
	@ResponseBody
	public String deleteDepartment(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			int i = DepartmentService.deleteDepartment(id);
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
	
	
	@RequestMapping(value = "/findDepartmentById.htm")
	@ResponseBody
	public String findDepartmentById(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			Map<String,Object> map = DepartmentService.findDepartmentById(id);
			if (map!=null) {
				orr.setCode(0);
				orr.setErrmsg("查询成功");
				orr.setItem(map);
			} else {
				orr.setCode(1);
				orr.setErrmsg("查询失败");
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
}
