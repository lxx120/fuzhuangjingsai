package com.match.controllers.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.news.service.ContentService;

@Controller
@RequestMapping(value = "/WebContent")
public class ContentWebController {

	@Resource
	private ContentService contentservice;
	
	@RequestMapping(value = "/findContentPageWeb.htm")
	@ResponseBody
	public String findContentPageWeb(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
			PageModel pageModel = new Pageparam().getPageParam(r);
			result = contentservice.findContentPageWeb(pageModel);
			if (result.getItems() != null && result.getItems().size() > 0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
			} else {
				result.setCode(1);
				result.setErrmsg("暂无数据");
			}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findContentByIDWeb.htm")
	@ResponseBody
	public String findContentByIDWeb(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
			long id = RequestParam.getLong(r, "id");
			if (0 == id) {
				orr.setCode(1);
				orr.setErrmsg("新闻id不能为空");
			} else {
				Map<String, Object> map = contentservice.findContentByID(id);
				orr.setCode(0);
				orr.setItem(map);
				orr.setErrmsg("查询成功");
			}
		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}
}
