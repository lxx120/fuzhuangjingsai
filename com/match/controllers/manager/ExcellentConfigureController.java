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
import com.match.common.result.ObjectResult;
import com.match.reviewresult.model.ExcellentConfigure;
import com.match.reviewresult.service.ExcellentConfigureService;

@Controller
@RequestMapping(value = "/ExcellentConfigure")
public class ExcellentConfigureController {
	
	@Autowired
	private ExcellentConfigureService  ExcellentConfigureService;
	
	@RequestMapping(value = "/addExcellentConfigure.htm")
	@ResponseBody
	public String addExcellentConfigure(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			ExcellentConfigure  excellentConfigure = new Zhuanbianduixiang().reflects(r, ExcellentConfigure.class);
			excellentConfigure.setCompetationid(getlogin.getCompetitionid());
			excellentConfigure.setDivisionid(getlogin.getDivisionid());
			int i  = ExcellentConfigureService.addExcellentConfigure(excellentConfigure);
			if (i>0) {
				orr.setCode(0);
				orr.setErrmsg("添加成功");
			} 
			else if(i==-1)
			{
				orr.setCode(1);
				orr.setErrmsg("已经存在不能重复添加");
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
	
	
	@RequestMapping(value = "/findExcellentConfigure.htm")
	@ResponseBody
	public String findExcellentConfigure(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Map<String,Object>  map  = ExcellentConfigureService.findExcellentConfigure(getlogin.getCompetitionid());
			if (map!=null) {
				orr.setCode(0);
				orr.setErrmsg("查询成功");
				orr.setItem(map);
			} 
			else {
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
	
	
	@RequestMapping(value = "/updateExcellentConfigure.htm")
	@ResponseBody
	public String updateExcellentConfigure(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			ExcellentConfigure  excellentConfigure = new Zhuanbianduixiang().reflects(r, ExcellentConfigure.class);
			int i  = ExcellentConfigureService.updateExcellentConfigure(excellentConfigure);
			if (i>0) {
				orr.setCode(0);
				orr.setErrmsg("修改成功");
			} 
			else {
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
