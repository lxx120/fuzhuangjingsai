package com.match.proposition.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.baciss.mapper.UserMapper;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.judges.mapper.AssessDaiWorkMapper;
import com.match.judges.model.AssessDaiWork;
import com.match.org.mapper.propositionstrategyMapper;
import com.match.org.model.Enterprise;
import com.match.org.model.propositionstrategy;
import com.match.proposition.cl.ComChangLiang;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.ThemeMapper;
import com.match.proposition.mapper.ThemeSignupMapper;
import com.match.proposition.mapper.ThemeTypeMapper;
import com.match.proposition.model.ChaXunTiaoJian;
import com.match.proposition.model.Competition;
import com.match.proposition.model.Theme;
import com.match.proposition.model.ThemeType;
import com.match.proposition.service.ThemeService;

@Service
public class ThemeServiceImpl implements ThemeService {

	@Resource
	private ThemeMapper themeMapper;

	@Autowired
	private ThemeTypeMapper ThemeTypeMapper;

	@Autowired
	private CompetitionMapper CompetitionMapper;

	@Autowired
	private UserMapper UserMapper;
	
	@Autowired
	private  ThemeSignupMapper  ThemeSignupMapper;
	
	@Autowired
	private  AssessDaiWorkMapper  AssessDaiWorkMapper;
	
	@Autowired
	private  propositionstrategyMapper  propositionstrategyMapper;
	

	@Override
	public int addTheme(Theme theme, ThemeType themeType,propositionstrategy propositionstrategy) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = CompetitionMapper.findCurrnetCompetition3();
		if (map != null) {
			
			if(ComChangLiang.ready==Integer.parseInt(map.get("status").toString()))
			{
				return 4;  //比赛尚未开始
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//当前时间
			Date s1 = sdf.parse(sdf.format(new Date()));
			//提交命题开始时间
			Date themestime = sdf.parse(map.get("themestime").toString());
			//提交命题结束时间
			Date themeetime = sdf.parse(map.get("themeetime").toString());
			Date commitstime = sdf.parse(map.get("commitstime").toString());
			Date commitetime = sdf.parse(map.get("commitetime").toString());
			
			if(s1.compareTo(themestime)<0)
			{
				return 5;  //还未到提交命题时间
			}
			if(s1.compareTo(themeetime)>0)
			{
				return 6;   //命题提交时间已过
			}
			if(commitstime.compareTo(sdf.parse(theme.getSingupsttime()))>=0)
			{
				return 7;  //作品提交开始时间不能早于比赛规定提交开始时间
			}
			if(commitetime.compareTo(sdf.parse(theme.getSingupendtime()))<=0)
			{
				return 8;  //作品提交结束时间不能晚于比赛规定提交结束时间
			}
			theme.setCompetitionid(Long.parseLong(map.get("id").toString()));
			theme.setMtime(UserChangLiang.mtime());
			theme.setStatus(ComChangLiang.ready);
			Map<String,Object>  maxcodemap = themeMapper.findMaxThemeCode();
			if(maxcodemap==null)
			{
				theme.setThemecode(String.format("%02d",1));
			}
			else
			{
				theme.setThemecode(String.format("%02d",Integer.parseInt(maxcodemap.get("themecode").toString())+1));
			}
			int i = themeMapper.insertSelective(theme);
			
			//添加到轮次待分配表里面
			AssessDaiWork assessDaiWork = new AssessDaiWork();
			assessDaiWork.setCompetitionid(theme.getCompetitionid());
			assessDaiWork.setMtime(UserChangLiang.mtime());
			assessDaiWork.setDivisionid(theme.getDivisionid());
			assessDaiWork.setWorkid(theme.getId());
			AssessDaiWorkMapper.addAssessDaiWork(assessDaiWork);
			
			if (i > 0) {
				String[] a = themeType.getPtcode().split(",");
				if (a.length == 0) {
					return 2; // 命题类型不能为空
				}
				List<ThemeType> list = new ArrayList<ThemeType>();
				for (int j = 0; j < a.length; j++) {
					ThemeType themeType2 = new ThemeType();
					themeType2.setMtime(UserChangLiang.mtime());
					themeType2.setPtcode(a[j]);
					themeType2.setThemeid(theme.getId());
					list.add(themeType2);
				}
				int i1 = ThemeTypeMapper.addBatchThemeType(list);
				
				//添加策略单
				propositionstrategy.setMtime(UserChangLiang.mtime());
				propositionstrategy.setCompetitionid(theme.getCompetitionid());
				propositionstrategy.setEnterpriseid(theme.getEnterpriseid());
				propositionstrategy.setThemeid(theme.getId());
				int i2 = propositionstrategyMapper.addPropositionstrategy(propositionstrategy);
				
				if (i1 > 0 && i2>0) {
					return 1;
				}
			}
			return -1;
		}
		return 3;
	}

	@Override
	public int updateTheme(Theme theme, ThemeType themeType) throws Exception {
		// TODO Auto-generated method stub

		Map<String, Object> map = themeMapper.findThemeInformatation(theme.getId());
		if (map != null) {
			
			if(ComChangLiang.ready==Integer.parseInt(map.get("status").toString()))
			{
				return 4;  //比赛尚未开始
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//当前时间
			Date s1 = sdf.parse(sdf.format(new Date()));
			//提交命题开始时间
			Date themestime = sdf.parse(map.get("themestime").toString());
			//提交命题结束时间
			Date themeetime = sdf.parse(map.get("themeetime").toString());
			Date commitstime = sdf.parse(map.get("commitstime").toString());
			Date commitetime = sdf.parse(map.get("commitetime").toString());
			
			if(s1.compareTo(themestime)<0)
			{
				return 5;  //还未到提交命题时间
			}
			if(s1.compareTo(themeetime)>0)
			{
				return 6;   //命题提交时间已过
			}
			if(commitstime.compareTo(sdf.parse(theme.getSingupsttime()))>0)
			{
				return 7;  //作品提交开始时间不能早于比赛规定提交开始时间
			}
			if(commitetime.compareTo(sdf.parse(theme.getSingupendtime()))<0)
			{
				return 8;  //作品提交结束时间不能晚于比赛规定提交结束时间
			}
			Date stime = sdf.parse(map.get("stime").toString());
			Date singupsttime = sdf.parse(map.get("singupsttime").toString());

			if (stime.compareTo(s1) > 0 || singupsttime.compareTo(s1) > 0) {
				theme.setMtime(UserChangLiang.mtime());
				int i = themeMapper.updateByPrimaryKeySelective(theme);
				if (i > 0) {
					// 删除之前的命题类型
					ThemeTypeMapper.deleteThemeTypeByThemeid(theme.getId());

					// 添加命题类型
					String[] a = themeType.getPtcode().split(",");
					if (a.length == 0) {
						return 2; // 命题类型不能为空
					}
					List<ThemeType> list = new ArrayList<ThemeType>();
					for (int j = 0; j < a.length; j++) {
						ThemeType themeType2 = new ThemeType();
						themeType2.setMtime(UserChangLiang.mtime());
						themeType2.setPtcode(a[j]);
						themeType2.setThemeid(theme.getId());
						list.add(themeType2);
					}
					int i1 = ThemeTypeMapper.addBatchThemeType(list);
					if (i1 > 0) {
						return 1;
					}
				}
				return -1;
			}
			return 2;

		}

		return -1;
	}

	@Override
	public boolean deleteTheme(long id) throws Exception {
		// TODO Auto-generated method stub
		int i = themeMapper.deleteByPrimaryKey(id);
		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> findTheme(long id) throws Exception {
		// TODO Auto-generated method stub
		return themeMapper.findTheme(id);
	}

	@Override
	public List<Map<String, Object>> findEnterpriseJoinTheme(long id) throws Exception {
		// TODO Auto-generated method stub
		return themeMapper.findEnterpriseJoinTheme(id);
	}

	@Override
	public PageResult<Map<String, Object>> findEnterpriseTheme(long userid, ChaXunTiaoJian chaXunTiaoJian,
			PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String, Object>> page = new PageResult<Map<String, Object>>();
		int myint = chaXunTiaoJian.getMy(); // 1全部 2自己
		Map map = CompetitionMapper.findCurrnetCompetition3();
		if (map != null) {
			long competitionid = Long.parseLong(map.get("id").toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date s1 = sdf.parse(sdf.format(new Date()));
			if (myint == 1) {
				List<Map<String, Object>> list = themeMapper.findfindEnterpriseTheme(chaXunTiaoJian.getPeople(),
						chaXunTiaoJian.getTime(), competitionid, pageModel.getPage(), pageModel.getPagesize());
				for (Map<String, Object> map2 : list) {
					if (sdf.parse(map.get("singupsttime").toString()).compareTo(s1) < 0
							&& sdf.parse(map.get("singupendtime").toString()).compareTo(s1) > 0) {
						map2.put("status", 1); // 可以报名
					} else if (sdf.parse(map.get("singupsttime").toString()).compareTo(s1) > 0) {
						map2.put("status", 2); // 还未到报名时间
					} else {
						map2.put("status", 3); // 报名时间已经过去
					}

				}

				page.setItems(list);
			}

			if (myint == 2) {
				// 查询角色是什么
				Map<String, Object> map1 = UserMapper.findUserCollege(userid, competitionid);
				if (5 != Integer.parseInt(map1.get("type").toString())) {
					page.setErrmsg("您不是学生无法参加");
					page.setCode(1);
					return page;
				}
				if (0 == Integer.parseInt(map1.get("isperfect").toString())) {
					page.setErrmsg("您还未完善资质，无法查询");
					page.setCode(1);
					return page;
				}
				if (0 == Integer.parseInt(map1.get("divisionid").toString())) {
					page.setErrmsg("您所在的学校还未被分配在赛区中，无法查询");
					page.setCode(1);
					return page;
				}
				if (0 == userid) {
					page.setErrmsg("还未登录无法查询");
					page.setCode(1);
					return page;
				}

				List<Map<String, Object>> list = themeMapper.findfindEnterpriseTheme1(
						Long.parseLong(map1.get("divisionid").toString()), chaXunTiaoJian.getPeople(),
						chaXunTiaoJian.getTime(), competitionid, pageModel.getPage(), pageModel.getPagesize());
				for (Map<String, Object> map2 : list) {
					if (sdf.parse(map.get("singupsttime").toString()).compareTo(s1) < 0
							&& sdf.parse(map.get("singupendtime").toString()).compareTo(s1) > 0) {
						map2.put("status", 1); // 可以报名
					} else if (sdf.parse(map.get("singupsttime").toString()).compareTo(s1) > 0) {
						map2.put("status", 2); // 还未到报名时间
					} else {
						map2.put("status", 3); // 报名时间已经过去
					}

				}
				page.setItems(list);
			}
		} else {
			page.setErrmsg("暂无进行的比赛");
			page.setCode(1);
		}
		return page;
	}

	@Override
	public Map<String,Object> findIsCanSignUp(long userid, long themeid) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> remap = new HashMap<String, Object>();
		Map<String,Object> map = CompetitionMapper.findCurrnetCompetition2();
		if(map!=null)
		{
			long competitionid = Long.parseLong(map.get("id").toString());
			//查询用户的一些信息
			Map<String,Object>  map1 = UserMapper.findUserCollege(userid, competitionid);
			if(5!=Integer.parseInt(map1.get("type").toString()))
			{
				remap.put("code", 3); // 您不是学生无法参加
				return remap;
			}
			if(0==Integer.parseInt(map1.get("isperfect").toString()))
			{
				remap.put("code", 4);  //您还未完善资质，无法查询
				return remap;
			}
			if(map1.get("divisionid")==null)
			{
				remap.put("code", 5);  //您所在的学校还未被分配在赛区中，无法查询
				return remap;
			}
			Map<String,Object> thememap = themeMapper.findThemeInformatation(themeid);
			if(!map1.get("divisionid").toString().equals(thememap.get("divisionid").toString()))
			{
				remap.put("code", 6);  //不是一个赛区不能报名
				return remap;
			}
			Map<String,Object> mapcount = ThemeSignupMapper.findSumThemeSignUpMember(themeid);
			if(Integer.parseInt(thememap.get("limitPeople").toString())<=Integer.parseInt(mapcount.get("count").toString()))
			{
				remap.put("code", 7);  // 人数超过报名上线
				return remap;
			}
			Map<String,Object> usermap = ThemeSignupMapper.findIsVisitTheme(themeid, userid);
			if(usermap!=null)
			{
				remap.put("code", 8);  //已经参加过
				remap.put("id", usermap.get("id"));
				return remap;
			}
			remap.put("code", 1);
			return remap;
			
		}
		 remap.put("code", 0); //还没有比赛
		 return remap;
	}

	@Override
	public void updateQuartzBatchTheme() throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  list = themeMapper.findupdateQuartzBatchTheme();
		List<Theme>  listtheme = new ArrayList<Theme>();
		if(list!=null && list.size()>0)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date s1 = sdf.parse(sdf.format(new Date()));
			for (Map<String, Object> map : list) {
				Theme theme = new Theme();
				theme.setMtime(UserChangLiang.mtime());
				theme.setId(Long.parseLong(map.get("id").toString()));
				Date singupsttime = sdf.parse(map.get("singupsttime").toString());
				Date singupendtime = sdf.parse(map.get("singupendtime").toString());
				Date stime = sdf.parse(map.get("stime").toString());
				Date etime = sdf.parse(map.get("etime").toString());
				if(0==Integer.parseInt(map.get("status").toString()))
				{
					if(s1.compareTo(singupsttime)>=0 && s1.compareTo(singupendtime)<0)
					{
						//设置为报名时间
						theme.setStatus(ComChangLiang.start);
						listtheme.add(theme);
					}
				}
				else if(1==Integer.parseInt(map.get("status").toString()))
				{
					if(s1.compareTo(singupendtime)>0 && s1.compareTo(stime)<0)
					{
						//设置为报名提交作品期间
						theme.setStatus(ComChangLiang.free);
						listtheme.add(theme);
					}
					else if(s1.compareTo(singupendtime)>0 && s1.compareTo(stime)>=0)
					{
						theme.setStatus(ComChangLiang.close);
						listtheme.add(theme);
					}
					
				}
				else if(2==Integer.parseInt(map.get("status").toString()))
				{
					if(s1.compareTo(etime)>=0)
					{
						//设置为报名时间
						theme.setStatus(ComChangLiang.end);
						listtheme.add(theme);
					}
				}
			}
		}
		
		themeMapper.batchUpdateTheme(listtheme);
		
	}

	/**
	 * 命题企业分页
	 */
	@Override
	public PageResult<Map<String, Object>> findThemepage(Theme theme, PageModel p) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		Map<String,Object>  map = CompetitionMapper.findCurrnetCompetition3();
		if(map==null)
		{
			prr.setItems(null);
			return prr;
		}
		theme.setCompetitionid(Long.parseLong(map.get("id").toString()));
		int count=themeMapper.count(theme);
		prr.setPages(Daomethod.countpages(count, p.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = themeMapper.findThemepage(Long.parseLong(map.get("id").toString()),p.getPage(),p.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public PageResult<Map<String, Object>> findThemeByEnterpriseId(long competitionid, long enterpriseid,
			PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=themeMapper.countfindThemeByEnterpriseId(competitionid, enterpriseid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = themeMapper.findThemeByEnterpriseId(competitionid,enterpriseid,pageModel.getPage(),pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public PageResult<Map<String, Object>> findThemepage1(Theme theme, PageModel p) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		Map<String,Object>  map = CompetitionMapper.findCurrnetCompetition3();
		if(map==null)
		{
			prr.setItems(null);
			return prr;
		}
		theme.setCompetitionid(Long.parseLong(map.get("id").toString()));
		int count=themeMapper.count1(theme);
		prr.setPages(Daomethod.countpages(count, p.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = themeMapper.findThemepage1(Long.parseLong(map.get("id").toString()),p.getPage(),p.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public Map<String, Object> findthemeGroupByWorktype(long themeid) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  list = themeMapper.findthemeGroupByWorktype(themeid);
		int count = themeMapper.countfindthemeGroupByWorktype(themeid);
		
		if(count!=0)
		{
			Map<String,Object>  map = new HashMap<String, Object>();
			map.put("sumcount", count);
			map.put("group", list);
			map.put("themeid", themeid);
			return map;
		}
		return null;
	}

	@Override
	public PageResult<Map<String, Object>> findthemeToSchool(String worktype, long themeid,PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=themeMapper.countfindthemeToSchool(worktype, themeid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = themeMapper.findthemeToSchool(worktype,themeid,pageModel.getPage(),pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

}
