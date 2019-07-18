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
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.judges.model.AssessRounds;
import com.match.judges.service.AssessRoundsService;
import com.match.news.model.Content;

@Controller
@RequestMapping(value = "/AssessRounds")
public class AssessRoundsController {

	@Autowired
	private AssessRoundsService AssessRoundsService;

	@RequestMapping(value = "/addAssessRounds.htm")
	@ResponseBody
	public String addAssessRounds(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			AssessRounds assessRounds = new Zhuanbianduixiang().reflects(r, AssessRounds.class);
			assessRounds.setDivisionid(getlogin.getDivisionid());
//			assessRounds.setDivisionid((long)16);
			int i = AssessRoundsService.addAssessRounds(assessRounds, getlogin.getDivisionid());
			if (i == 1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			}
			else if(i==5)
			{
				result.setCode(1);
				result.setErrmsg("命题提交截止时间未到，不能开轮次");
			}
			else if (i == -3) {
				result.setCode(1);
				result.setErrmsg("新增轮次的开始时间不能小于当前时间");
			} else if (i == -2) {
				result.setCode(1);
				result.setErrmsg("暂无竞赛，无法添加");
			} else if (i == -4) {
				result.setCode(1);
				result.setErrmsg("轮次评判最终时间未到，不能新添轮次");
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

	@RequestMapping(value = "/deleteAssessRounds.htm")
	@ResponseBody
	public String deleteAssessRounds(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		long id = RequestParam.getLong(r, "id");
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			int i = AssessRoundsService.deleteAssessRounds(id, getlogin.getDivisionid());
			if (i == 1) {
				orr.setCode(0);
				orr.setErrmsg("删除成功");
			} else if (i == 2) {
				orr.setCode(1);
				orr.setErrmsg("轮次正在进行中，无法修改");
			} else if (i == 3) {
				orr.setCode(1);
				orr.setErrmsg("轮次进行完毕，无法修改");
			} else if (i == 4) {
				orr.setCode(1);
				orr.setErrmsg("暂无进行的比赛或者轮次");
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

	@RequestMapping(value = "/findAssessRounds.htm")
	@ResponseBody
	public String findAssessRounds(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
//		long id = RequestParam.getLong(r, "id");
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			AssessRounds assessRounds = new AssessRounds();
			assessRounds.setDivisionid(getlogin.getDivisionid());
			List<Map<String, Object>> map = AssessRoundsService.findAssessRoundsPage(assessRounds,
					getlogin.getDivisionid());
			if (map != null && map.size() > 0) {
				orr.setCode(0);
				orr.setItem(map);
				orr.setErrmsg("查询成功");
			} else if(map==null) {
				orr.setCode(1);
				orr.setErrmsg("暂无进行或者准备进行的竞赛");
			}
			else
			{
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

	@RequestMapping(value = "/updateAssessRounds.htm")
	@ResponseBody
	public String updateAssessRounds(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			AssessRounds assessRounds = gson.fromJson(s,AssessRounds.class);
			int i = AssessRoundsService.updateAssessRounds(assessRounds, getlogin.getDivisionid());
			if (i == 1) {
				result.setCode(0);
				result.setErrmsg("修改成功");
			} else if (i == 2) {
				result.setCode(1);
				result.setErrmsg("轮次正在进行中，无法修改");
			} else if (i == 3) {
				result.setCode(1);
				result.setErrmsg("轮次进行完毕，无法修改");
			} else if (i == 4) {
				result.setCode(1);
				result.setErrmsg("暂无进行的比赛或者轮次");
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
	
	
	@RequestMapping(value = "/findAssessRoundsById.htm")
	@ResponseBody
	public String findAssessRoundsById(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
//		long id = RequestParam.getLong(r, "id");
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Long id = RequestParam.getLong(r, "id");
			Map<String, Object> map = AssessRoundsService.findAssessRoundsById(id);
			if (map != null && map.size() > 0) {
				orr.setCode(0);
				orr.setItem(map);
				orr.setErrmsg("查询成功");
			} 
			else
			{
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
	
	
	@RequestMapping(value = "/addDistributionByArid.htm")
	@ResponseBody
	public String addDistributionByArid(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			long arid = RequestParam.getLong(r, "arid");
			String code = RequestParam.getString(r, "code");
			if(code!=null && !code.equals(""))
			{
				result = AssessRoundsService.addDistributionByArid(code,arid, pageModel);
			
			}
			else
			{
				result.setCode(0);
				result.setErrmsg("请选择作别类别");
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
