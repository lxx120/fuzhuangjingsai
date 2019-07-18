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
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.service.ThemeWorkService;

@Controller
@RequestMapping(value = "/ThemeWork")
public class ThemeWorkController {
	
	@Autowired
	private ThemeWorkService  ThemeWorkService;
	
	
	@RequestMapping(value = "/findAllThemeWorkByCompetitionid.htm")
	@ResponseBody
	public String findAllThemeWorkByCompetitionid(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			//作品名称
			String  title = RequestParam.getSqlString(r, "title");
			//作品类型
			String  code = RequestParam.getSqlString(r, "code");
			result = ThemeWorkService.findAllThemeWorkByCompetitionid(title,code,getlogin.getCompetitionid(), pageModel);
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
	
	
	@RequestMapping(value = "/findAllThemeWorkByCompetitionidDivi.htm")
	@ResponseBody
	public String findAllThemeWorkByCompetitionidDivi(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			//作品名称
			String  title = RequestParam.getSqlString(r, "title");
			//作品类型
			String  code = RequestParam.getSqlString(r, "code");
			
			result = ThemeWorkService.findAllThemeWorkByCompetitionidDivi(getlogin.getDivisionid(),title,code,getlogin.getCompetitionid(), pageModel);
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
	
	
	@RequestMapping(value = "/findAllThemeWorkByCompetitionidJinJi.htm")
	@ResponseBody
	public String findAllThemeWorkByCompetitionidJinJi(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			//作品名称
			String  title = RequestParam.getSqlString(r, "title");
			//作品类型
			String  code = RequestParam.getSqlString(r, "code");
			
			result = ThemeWorkService.findAllThemeWorkByCompetitionidJinJi(title,code,getlogin.getCompetitionid(), pageModel);
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
	
	
	@RequestMapping(value = "/findThemeOverScore.htm")
	@ResponseBody
	public String findThemeOverScore(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String,Object>>> orr = new ObjectResult<List<Map<String,Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
//		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			List<Map<String,Object>> list = ThemeWorkService.findThemeOverScore(id);
			if (list!=null) {
				orr.setCode(0);
				orr.setErrmsg("查询成功");
				orr.setItem(list);
			} else {
				orr.setCode(1);
				orr.setErrmsg("查询失败");
			}
//		} else {
//			orr.setCode(1);
//			orr.setErrmsg("尚未登录，无法获取数据");
//		}
		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}

}
