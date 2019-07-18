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
import com.match.common.result.PageResult;
import com.match.judges.model.CompetitionAprize;
import com.match.judges.service.CompetitionAprizeService;

@Controller
@RequestMapping(value = "/CompetitionAprize")
public class CompetitionAprizeController {

	@Autowired
	private CompetitionAprizeService CompetitionAprizeService;

	@RequestMapping(value = "/addCompetitionAprize.htm")
	@ResponseBody
	public String addCompetitionAprize(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			CompetitionAprize CompetitionAprize = new Zhuanbianduixiang().reflects(r, CompetitionAprize.class);
//		Gson gson = new Gson();
			CompetitionAprize.setDivisionid(getlogin.getDivisionid());
			CompetitionAprize.setCompetitionid(getlogin.getCompetitionid());
			boolean flag = CompetitionAprizeService.addCompetitionAprize(CompetitionAprize);
			if (flag) {
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

	@RequestMapping(value = "/deleteCompetitionAprize.htm")
	@ResponseBody
	public String deleteCompetitionAprize(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		long id = RequestParam.getLong(r, "id");
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			boolean flag = CompetitionAprizeService.deleteCompetitionAprize(id);
			if (flag) {
				orr.setCode(0);
				orr.setErrmsg("删除成功");
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

	@RequestMapping(value = "/findCompetitionAprize.htm")
	@ResponseBody
	public String findCompetitionAprize(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long arid = RequestParam.getLong(r, "arid");
			CompetitionAprize CompetitionAprize = new CompetitionAprize();
			CompetitionAprize.setDivisionid(getlogin.getDivisionid());
			List<Map<String, Object>> list = CompetitionAprizeService.findCompetitionAprize(arid);
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
	
	
	@RequestMapping(value = "/findCompetitionAprizeById.htm")
	@ResponseBody
	public String findCompetitionAprizeById(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			Map<String, Object> list = CompetitionAprizeService.findCompetitionAprizeById(id);
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

	@RequestMapping(value = "/updateCompetitionAprize.htm")
	@ResponseBody
	public String updateCompetitionAprize(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			CompetitionAprize CompetitionAprize = new Zhuanbianduixiang().reflects(r, CompetitionAprize.class);
			boolean flag = CompetitionAprizeService.updateCompetitionAprize(CompetitionAprize);
			if (flag) {
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

	@RequestMapping(value = "/addBatchCompetitionAprize.htm")
	@ResponseBody
	public String addBatchCompetitionAprize(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			List<Map<String, Object>> list = gson.fromJson(s, List.class);
			boolean flag = CompetitionAprizeService.addBatchCompetitionAprize(getlogin.getDivisionid(), list);
			if (flag) {
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

	@RequestMapping(value = "/findPrizeList.htm")
	@ResponseBody
	public String findPrizeList(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			List<Map<String, Object>> list = CompetitionAprizeService.findPrizeList(getlogin.getDivisionid());
			if (list != null && list.size() > 0) {
				orr.setCode(0);
				orr.setItem(list);
				orr.setErrmsg("查询成功");
			} else if (list == null) {
				orr.setCode(1);
				orr.setErrmsg("没有进行的比赛或者开启的轮次");
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
	
	
	@RequestMapping(value = "/findPrizeBYHuoJiang.htm")
	@ResponseBody
	public String findPrizeBYHuoJiang(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			List<Map<String, Object>> list = CompetitionAprizeService.findPrizeBYHuoJiang(getlogin.getCompetitionid(), getlogin.getDivisionid());
			if (list != null && list.size() > 0) {
				orr.setCode(0);
				orr.setItem(list);
				orr.setErrmsg("查询成功");
			} else if (list == null) {
				orr.setCode(1);
				orr.setErrmsg("没有进行的比赛或者开启的轮次");
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
	
	
	
	@RequestMapping(value = "/findHuoJiangThemeWork.htm")
	@ResponseBody
	public String findHuoJiangThemeWork(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String code = RequestParam.getString(r, "code");
			List<Map<String, Object>> list = CompetitionAprizeService.findHuoJiangThemeWork(code, getlogin.getDivisionid(), getlogin.getCompetitionid());
			if (list != null && list.size() > 0) {
				orr.setCode(0);
				orr.setItem(list);
				orr.setErrmsg("查询成功");
			} else if (list == null) {
				orr.setCode(1);
				orr.setErrmsg("暂无数据");
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
}
