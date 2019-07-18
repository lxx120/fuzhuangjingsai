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
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.reviewresult.model.Peizhi;
import com.match.reviewresult.service.PeizhiService;

@Controller
@RequestMapping(value = "/Peizhi")
public class PeizhiController {

	@Autowired
	private  PeizhiService  PeizhiService;
	
	@RequestMapping(value = "/addPeizhi.htm")
	@ResponseBody
	public String findAllTeacher(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Peizhi peizhi = new Zhuanbianduixiang().reflects(r, Peizhi.class);
			int i = PeizhiService.addPeizhi(peizhi);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("成功");
			} else {
				result.setCode(1);
				result.setErrmsg("失败");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findPeizhi.htm")
	@ResponseBody
	public String findPeizhi(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			int type = RequestParam.getInt(r, "type");
			Map<String,Object> map = PeizhiService.findPeizhi(type);
			if (map!=null) {
				result.setCode(0);
				result.setItem(map);
				result.setErrmsg("成功");
			} else {
				result.setCode(1);
				result.setErrmsg("失败");
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
