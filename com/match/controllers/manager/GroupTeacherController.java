package com.match.controllers.manager;

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
import com.match.common.Jacksonmethod;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.judges.model.GroupTeacher;
import com.match.judges.service.GroupTeacherService;

@Controller
@RequestMapping(value = "/GroupTeacher")
public class GroupTeacherController {

	@Autowired
	private GroupTeacherService GroupTeacherservice;

	@RequestMapping(value = "/findGroupTeacher.htm")
	@ResponseBody
	public String findGroupTeacher(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		long groupid = RequestParam.getLong(r, "groupid");
		if (getlogin != null) {
			List<Map<String, Object>> list = GroupTeacherservice.findGroupTeacherList(groupid);
			if (list != null && list.size() > 0) {
				orr.setCode(0);
				orr.setItem(list);
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

	@RequestMapping(value = "/deleteGroupTeacher.htm")
	@ResponseBody
	public String deleteGroupTeacher(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		long id = RequestParam.getLong(r, "id");
		long divisionid = RequestParam.getLong(r, "divisionid");
		long groupid = RequestParam.getLong(r, "groupid");
		long arid = RequestParam.getLong(r, "arid");
		long teacherid = RequestParam.getLong(r, "teacherid");
		if (getlogin != null) {
			boolean flag = GroupTeacherservice.deleteGroupTeacher(id, divisionid, groupid, arid,teacherid);
			if (flag) {
				orr.setCode(0);
				orr.setErrmsg("删除成功,请重新分配权重");
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

	@RequestMapping(value = "/addGroupTeacher.htm")
	@ResponseBody
	public String addGroupTeacher(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		String s = RequestParam.getString(r, "json");
		Gson gson = new Gson();
		GroupTeacher groupTeacher = gson.fromJson(s,GroupTeacher.class);
		if (getlogin != null) {
			boolean flag = GroupTeacherservice.addGroupTeacher(groupTeacher);
			if (flag) {
				orr.setCode(0);
				orr.setErrmsg("添加成功,请去分配老师权重");
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

	@RequestMapping(value = "/updateBatchGroupTeacher.htm")
	@ResponseBody
	public String updateBatchGroupTeacher(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		String s = RequestParam.getString(r, "json");
		Gson gson = new Gson();
		GroupTeacher groupTeacher = gson.fromJson(s,GroupTeacher.class);
		if (getlogin != null) {
			boolean flag = GroupTeacherservice.updateBatchGroupTeacher(groupTeacher.getJudhelist());
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
}
