package com.match.controllers.manager;

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
import com.match.org.model.Zhengshuload;
import com.match.org.service.ZhengshuloadService;

@Controller
@RequestMapping(value = "/Zhengshu")
public class ZhengshuloadController {

	@Autowired
	private  ZhengshuloadService  ZhengshuloadService;
	
	@RequestMapping(value = "/addZhengshu.htm")
	@ResponseBody
	public String addZhengshu(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Zhengshuload zhengshuload = new Zhuanbianduixiang().reflects(r, Zhengshuload.class);
			zhengshuload.setCompetitionid(getlogin.getCompetitionid());
			int i = ZhengshuloadService.addZhengshu(zhengshuload);
			if (i > 0) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} else {
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
	
	
	@RequestMapping(value = "/updateZhengshu.htm")
	@ResponseBody
	public String updateZhengshu(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Zhengshuload zhengshuload = new Zhuanbianduixiang().reflects(r, Zhengshuload.class);
			int i = ZhengshuloadService.updateZhengshu(zhengshuload);
			if (i > 0) {
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
	
	
	@RequestMapping(value = "/finfzhengshu.htm")
	@ResponseBody
	public String finfzhengshu(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			Map<String,Object>  map = ZhengshuloadService.finfzhengshu(id);
			if (map!=null) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(map);
			} else {
				result.setCode(1);
				result.setErrmsg("查询失败");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findzhengshulist.htm")
	@ResponseBody
	public String findzhengshulist(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String,Object>>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			List<Map<String,Object>>  map = ZhengshuloadService.findzhengshulist(getlogin.getCompetitionid());
			if (map!=null) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(map);
			} else {
				result.setCode(1);
				result.setErrmsg("查询失败");
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
