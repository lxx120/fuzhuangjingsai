package com.match.controllers.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.judges.service.AssessRoundsService;

@Controller
@RequestMapping(value = "/AssessRoundsWeb")
public class AssessRoundsWebController {

	@Autowired
	private  AssessRoundsService  AssessRoundsService;
	
	@RequestMapping(value = "/addDistributionByArid.htm")
	@ResponseBody
	public String addDistributionByArid(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		PageModel pageModel = new Pageparam().getPageParam(r);
		long arid = RequestParam.getLong(r, "arid");
		String code = RequestParam.getString(r, "code");
		if(code!=null && !code.equals(""))
		{
			result = AssessRoundsService.addDistributionByArid(code,arid, pageModel);
			if (result.getItems() != null && result.getItems().size() > 0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
			} else {
				result.setCode(1);
			}
		}
		else
		{
			result.setCode(0);
			result.setErrmsg("请选择作别类别");
		}
		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
}
