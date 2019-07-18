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
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.PageResult;
import com.match.judges.model.AssessRole;
import com.match.judges.service.AssessRoleService;

@Controller
@RequestMapping(value = "/AssessRole")
public class AssessRoleController {

	@Autowired
	private AssessRoleService AssessRoleService;

	@RequestMapping(value = "/addAssessRole.htm")
	@ResponseBody
	public String addAssessRole(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
//		if (getlogin != null) {
			AssessRole assessRole = new Zhuanbianduixiang().reflects(r, AssessRole.class);
			if (0 == assessRole.getGroupnum()) {
				result.setCode(1);
				result.setErrmsg("设置分组数不能为0");
			} else {
				boolean flag = AssessRoleService.addAssessRole(assessRole);
				if (flag) {
					result.setCode(0);
					result.setErrmsg("添加成功");
				} else {
					result.setCode(1);
					result.setErrmsg("添加失败");
				}
			}

//		} else {
//			result.setCode(1);
//			result.setErrmsg("尚未登录，无法获取数据");
//		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}

	@RequestMapping(value = "/updateAssessRole.htm")
	@ResponseBody
	public String updateAssessRole(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			AssessRole assessRole = new Zhuanbianduixiang().reflects(r, AssessRole.class);
			if (0 == assessRole.getGroupnum()) {
				result.setCode(1);
				result.setErrmsg("分组数不能为0");
			} else {
				boolean flag = AssessRoleService.updateAssessRole(assessRole);
				if (flag) {
					result.setCode(0);
					result.setErrmsg("修改成功");
				} else {
					result.setCode(1);
					result.setErrmsg("修改失败");
				}
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
