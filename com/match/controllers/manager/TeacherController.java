package com.match.controllers.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.baciss.model.LoginMessage;
import com.match.baciss.service.TeacherService;
import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;

@Controller
@RequestMapping(value = "/Teacher")
public class TeacherController {

	@Autowired
	private  TeacherService  TeacherService;
	
	@RequestMapping(value = "/findAllTeacher.htm")
	@ResponseBody
	public String findAllTeacher(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			String name = RequestParam.getString(r, "name");
			String phone = RequestParam.getString(r, "phone");
			long collegeid = RequestParam.getLong(r, "collegeid");
			long departmentid = RequestParam.getLong(r, "departmentid");
			result = TeacherService.findAllTeacher(name, phone, collegeid, departmentid, pageModel);
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
