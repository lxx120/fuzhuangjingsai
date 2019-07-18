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
import com.match.common.result.ObjectResult;
import com.match.reviewresult.service.ExcellentPartmentService;

@Controller
@RequestMapping(value = "/ExcellentPartment")
public class ExcellentPartmentController {

	
	@Autowired
	private  ExcellentPartmentService  ExcellentPartmentService;
	
	@RequestMapping(value = "/findAllExcellentPartment.htm")
	@ResponseBody
	public String findAllExcellentPartment(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long collegeid = RequestParam.getLong(r, "collegeid");
			List<Map<String,Object>>  list = ExcellentPartmentService.findAllExcellentPartment(getlogin.getCompetitionid(), collegeid);
			if (list!=null && list.size()>0) {
				orr.setCode(0);
				orr.setErrmsg("查询成功");
				orr.setItem(list);
			} else {
				orr.setCode(1);
				orr.setErrmsg("暂无数据");
			}
		} else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
}
