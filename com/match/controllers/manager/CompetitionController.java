package com.match.controllers.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
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
import com.match.proposition.model.Competition;
import com.match.proposition.model.Competition1;
import com.match.proposition.service.CompetitionService;

@Controller
@RequestMapping(value="/Competition" )
public class CompetitionController {

	@Autowired
	private  CompetitionService  CompetitionService;
	
	@RequestMapping(value="/addCompetition.htm" )
	@ResponseBody
	public  String  addCompetition(HttpServletRequest r , HttpServletResponse re) throws Exception
	{
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if(getlogin != null) {
				String s = RequestParam.getString(r, "json");
				Gson gson = new Gson();
				Competition1 competition1 = gson.fromJson(s, Competition1.class);
				Competition competition = new Competition();
				BeanUtils.copyProperties(competition1, competition);
				if(competition.getStime()!=null && competition.getEtime()!=null && competition.getCommitetime()!=null && competition.getCommitstime()!=null && competition.getThemeetime()!=null && competition.getThemestime()!=null)
				{
					List list1 = competition1.getDivisionList();
					int i  = CompetitionService.addCompetition(competition,  list1);
					if(i==1)
					{
						result.setCode(0);
						result.setErrmsg("添加成功");
					}
					else if(i==-1)
					{
						result.setCode(1);
						result.setErrmsg("添加失败");
					}
					else if(i==0)
					{
						result.setCode(1);
						result.setErrmsg("有比赛正在进行，无法添加");
					}
				}
				else
				{
					result.setCode(1);
					result.setErrmsg("时间不能为空");
				}
			
		}else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}
		
		String tojson = Jacksonmethod.tojson(result, false);
 		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value="/deleteCompetition.htm" )
	@ResponseBody
	public String  deleteCompetition(HttpServletRequest r, HttpServletResponse re)  throws  Exception
	{
		ObjectResult<Map<String,Object>> orr = new ObjectResult<Map<String,Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if(getlogin != null) {
		long id = RequestParam.getLong(r, "id");
		int i  = CompetitionService.deleteCompetition(id);
			if (i == 1) {
				orr.setCode(0);
				orr.setErrmsg("删除成功");
			} else if (i == 2) {
				orr.setCode(1);
				orr.setErrmsg("比赛期间不能删除");
			} else if (i == 3) {
				orr.setCode(1);
				orr.setErrmsg("比赛结束不能删除");
			} else {
				orr.setCode(1);
				orr.setErrmsg("删除失败");
			}
		}else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法获取数据");
		}
		String str="";
		str=Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}
	
	@RequestMapping(value="/findCompetition.htm" )
	@ResponseBody
	public  String  findCompetition(HttpServletRequest r , HttpServletResponse re)  throws  Exception
	{
		ObjectResult<Map<String,Object>>  orr = new  ObjectResult<Map<String,Object>>(); 
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if(getlogin != null) {
		long id = RequestParam.getLong(r, "id");
		if(0==id)
		{
			orr.setCode(1);
			orr.setErrmsg("竞赛id不能为空");
		}
		else
		{
			Map<String,Object>  map = CompetitionService.findCompetitionById(id);
			orr.setCode(0);
			orr.setItem(map);
			orr.setErrmsg("查询成功");
		}
		}else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法获取数据");
		}
		String str="";
		str=Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}
	
	@RequestMapping(value="/updateCompetition.htm" )
	@ResponseBody
	public  String  updateCompetition(HttpServletRequest r , HttpServletResponse re) throws Exception
	{
		PageResult<Map<String, Object>> orr = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if(getlogin != null) {
			String s = RequestParam.getString(r, "json");
			Gson gson = new Gson();
			Competition competition = gson.fromJson(s, Competition.class);
			 int i   = CompetitionService.updateCompetition(competition);
			 if(i==1)
				{
					orr.setCode(0);
					orr.setErrmsg("修改成功");
				}
				else if(i==2)
				{
					orr.setCode(1);
					orr.setErrmsg("比赛期间不能修改");
				}
				else if(i==3)
				{
					orr.setCode(1);
					orr.setErrmsg("比赛结束不能修改");
				}
				else
				{
					orr.setCode(1);
					orr.setErrmsg("修改失败");
				}
		}else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法获取数据");
		}
		
		String tojson = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	@RequestMapping(value="/findCompetitionPage.htm" )
	@ResponseBody
	public  String  findCompetitionPage(HttpServletRequest r , HttpServletResponse re) throws Exception
	{
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if(getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			Competition competition=new Zhuanbianduixiang().reflects(r,Competition.class);
			result = CompetitionService.findCompetitionPage(competition, pageModel);
			if(result.getItems()!=null && result.getItems().size()>0)
			{
				result.setCode(0);
				result.setErrmsg("查询成功");
			}
			else
			{
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
}
