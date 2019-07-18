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
import com.match.common.Pageparam;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.reviewresult.service.ExcellentCollegeService;

@Controller
@RequestMapping(value = "/ExcellentCollege")
public class ExcellentCollegeController {

	@Autowired
	private  ExcellentCollegeService  ExcellentCollegeService;
	
	@RequestMapping(value = "/findExcellentCollege.htm")
	@ResponseBody
	public String findExcellentCollege(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			result = ExcellentCollegeService.findExcellentCollege(pageModel);
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
	
	
	@RequestMapping(value = "/findExcellentCollege1.htm")
	@ResponseBody
	public String findExcellentCollege1(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> result = new ObjectResult<List<Map<String, Object>>>();
			List<Map<String,Object>> list = ExcellentCollegeService.findExcellentCollege1();
			if (list != null && list.size() > 0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItem(list);
			} else {
				result.setCode(1);
				result.setErrmsg("暂无数据");
			}
		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
}
