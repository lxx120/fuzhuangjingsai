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
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.org.model.DepartMajor;
import com.match.org.model.Department;
import com.match.org.service.DepartMajorService;

@Controller
@RequestMapping(value = "/WebDepartMajor")
public class DepartMajorWebController {

	@Resource
	private  DepartMajorService  DepartMajorService;
	
	@RequestMapping(value = "/findDepartMajorList.htm")
	@ResponseBody
	public String findDepartMajorList(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		DepartMajor departMajor = new Zhuanbianduixiang().reflects(r, DepartMajor.class);
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
//		if (getlogin != null) {
		List<Map<String, Object>> lsit = DepartMajorService.findDepartMajorList(departMajor);
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
	
	
	@RequestMapping(value = "/addDepartMajor.htm")
	@ResponseBody
	public String addDepartMajor(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			DepartMajor departMajor = new Zhuanbianduixiang().reflects(r, DepartMajor.class);
			boolean flag = DepartMajorService.addDepartMajor(departMajor);
			if (flag) {
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
	
	
	@RequestMapping(value = "/updateDepartMajor.htm")
	@ResponseBody
	public String updateDepartMajor(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			DepartMajor departMajor = new Zhuanbianduixiang().reflects(r, DepartMajor.class);
			boolean flag = DepartMajorService.updateDepartMajor(departMajor);
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
	
	
	@RequestMapping(value = "/deleteDepartMajor.htm")
	@ResponseBody
	public String deleteDepartMajor(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			DepartMajor departMajor = new Zhuanbianduixiang().reflects(r, DepartMajor.class);
			boolean flag = DepartMajorService.deleteDepartMajor(departMajor);
			if (flag) {
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
	
	
	@RequestMapping(value = "/findDepartMajorByid.htm")
	@ResponseBody
	public String findDepartMajorByid(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			Map<String,Object> map = DepartMajorService.findDepartMajorByid(id);
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
