package com.match.proposition.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.judges.mapper.AssessDaiWorkMapper;
import com.match.judges.mapper.CompetitionAprizeMapper;
import com.match.judges.model.AssessDaiWork;
import com.match.proposition.cl.CheatReportChangLiang;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.ThemeSignupMapper;
import com.match.proposition.mapper.ThemeWorkFileMapper;
import com.match.proposition.mapper.ThemeWorkMapper;
import com.match.proposition.model.ChaXunTiaoJian1;
import com.match.proposition.model.ThemeSignup;
import com.match.proposition.model.ThemeWork;
import com.match.proposition.service.ThemeWorkService;

@Service
public class ThemeWorkServiceImpl implements ThemeWorkService {

	@Autowired
	private ThemeWorkMapper ThemeWorkMapper;

	@Autowired
	private ThemeSignupMapper ThemeSignupMapper;

	@Autowired
	private ThemeWorkFileMapper ThemeWorkFileMapper;
	
	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	
	@Autowired
	private  CompetitionAprizeMapper  CompetitionAprizeMapper;
	
	@Autowired
	private  AssessDaiWorkMapper  AssessDaiWorkMapper;

	@Override
	public int addThemeWork(ThemeWork themeWork) throws Exception {

		// 添加命题的一些东西
		// 查询命题编号，赛区编号，学校编号
		Map<String, Object> codemap = ThemeSignupMapper.findAllCode(themeWork.getThemesignupid());
		//查询比赛的提交作品时间
		Map<String,Object>  compemap = CompetitionMapper.findCurrnetCompetition3();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = sdf.parse(sdf.format(new Date()));
		Date commitstime = sdf.parse(compemap.get("commitstime").toString());
		Date commitetime = sdf.parse(compemap.get("commitetime").toString());
		if(s1.compareTo(commitstime)<0)
		{
			return 2;
		}
		if(s1.compareTo(commitetime)>0)
		{
			return 3;
		}
		
		//查询轮次的所对应的奖项配置
		List<Map<String,Object>> listar = CompetitionAprizeMapper.findNowPrize(themeWork.getCompetitionid(), themeWork.getDivisionid());
		if(listar.size()==0)
		{
			return 4; //没有配置轮次
		}
		
		// 查询学校有多少人
		Map<String, Object> schoolmap = ThemeWorkMapper
				.findcountPeople(Long.parseLong(codemap.get("collegeid").toString()));
		String scnum;
		if (0 == Integer.parseInt(schoolmap.get("count").toString())) {
			scnum = String.format("%04d", 1);
		} else {
			scnum = String.format("%04d", Integer.parseInt(schoolmap.get("count").toString()) + 1);
		}
		codemap.get("workType") ;
		
		
		String twcode = codemap.get("workType") + String.format("%02d",Integer.parseInt(codemap.get("themecode").toString())) + "-"
				+ String.format("%02d", Integer.parseInt(codemap.get("divisioncode").toString())) + "-"
				+ String.format("%03d", Integer.parseInt(codemap.get("collegecode").toString())) + "-" + scnum;
		themeWork.setTwcode(twcode);
		themeWork.setMtime(UserChangLiang.mtime());
		themeWork.setCheat(CheatReportChangLiang.no);
		Map<String,Object>  mapar = listar.get(0);
		themeWork.setFinalaprizeid(Long.parseLong(mapar.get("id").toString()));
		int i = ThemeWorkMapper.insertSelective(themeWork);

		List<Map<String, Object>> list = themeWork.getList();
		int i1 = 1;
		if (list != null && list.size() > 0) {
			int k = 1;
			for (Map<String, Object> map : list) {
				map.put("mtime", UserChangLiang.mtime());
				map.put("themeworkid", themeWork.getId());
				map.put("code", twcode + "-" + k);
				k = k + 1;
			}

			i1 = ThemeWorkFileMapper.BatchAddThemeWorkFile(list);
		}
		
		//提交作品时，把报名的是否提交作品的状态改掉
		ThemeSignup themeSignup = new ThemeSignup();
		themeSignup.setId(themeWork.getThemesignupid());
		themeSignup.setPostworks(1);
		ThemeSignupMapper.updateByPrimaryKeySelective(themeSignup);
		
		//添加到待评审
		AssessDaiWork assessDaiWork = new AssessDaiWork();
		assessDaiWork.setCompetitionid(themeWork.getCompetitionid());
		assessDaiWork.setDivisionid(themeWork.getDivisionid());
		assessDaiWork.setMtime(UserChangLiang.mtime());
		assessDaiWork.setWorkid(themeWork.getId());
		assessDaiWork.setArid(Long.parseLong(mapar.get("arid").toString()));
		AssessDaiWorkMapper.addAssessDaiWork(assessDaiWork);
		
		if (i > 0 && i1 > 0) {
			return 1;
		}
		return -1;

	}

	@Override
	public int updateThemeWork(ThemeWork themeWork) throws Exception {

		//查询比赛的提交作品时间
		Map<String,Object>  compemap = CompetitionMapper.findCurrnetCompetition3();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = sdf.parse(sdf.format(new Date()));
		Date commitstime = sdf.parse(compemap.get("commitstime").toString());
		Date commitetime = sdf.parse(compemap.get("commitetime").toString());
		if(s1.compareTo(commitstime)<0)
		{
			return 2;
		}
		if(s1.compareTo(commitetime)>0)
		{
			return 3;
		}
		
		themeWork.setMtime(UserChangLiang.mtime());

		int i = ThemeWorkMapper.updateByPrimaryKeySelective(themeWork);

		ThemeWorkFileMapper.deleteThemeWorkByThemeworkid(themeWork.getId());

		List<Map<String, Object>> list = themeWork.getList();

		int i1 = 1;
		if (list != null && list.size() > 0) {
			int k = 1;
			for (Map<String, Object> map : list) {
				map.put("mtime", UserChangLiang.mtime());
				map.put("themeworkid", themeWork.getId());
				map.put("code", themeWork.getTwcode() + "-" + k);
				k = k + 1;
			}

			i1 = ThemeWorkFileMapper.BatchAddThemeWorkFile(list);
		}

		if (i > 0 && i1 > 0) {
			return 1;
		}
		return -1;
	}

	@Override
	public Map<String, Object> findMyThemeWork(long themesignupid) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = ThemeWorkMapper.findMyThemeWork(themesignupid);
		if(map!=null && map.size()>0)
		{
			map.put("file",ThemeWorkFileMapper.findMyThemeWorkFile(Long.parseLong(map.get("id").toString())));
		}
		return null;
	}

	@Override
	public PageResult<Map<String, Object>> findThemeWorkByOwner(long userid,long competitionid, long enterpriseid,
			PageModel pageModel) throws Exception {
		
		//当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = sdf.parse(sdf.format(new Date()));
		
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=ThemeWorkMapper.countfindThemeWorkByOwner(userid, competitionid, enterpriseid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = ThemeWorkMapper.findThemeWorkByOwner(userid,competitionid,enterpriseid,pageModel.getPage(),pageModel.getPagesize());
		for (Map<String, Object> map : ml) {
			
			//获取报名时间个提交作品时间
			Date singupsttime = sdf.parse(map.get("singupsttime").toString());
			Date singupendtime = sdf.parse(map.get("singupendtime").toString());
			Date commitstime = sdf.parse(map.get("commitstime").toString());
			Date commitetime = sdf.parse(map.get("commitetime").toString());
			
			if(s1.compareTo(singupsttime)>0 && s1.compareTo(singupendtime)<0)
			{
				//可以修改报名
				map.put("singupstatus", 1);
			}
			
			if(s1.compareTo(commitstime)>0 && s1.compareTo(commitetime)<0)
			{
				//作品提交修改
				map.put("commitstatus", 1);
			}
			
		}
		prr.setItems(ml);
		return prr;
	}

	@Override
	public PageResult<Map<String, Object>> findMyWork(long userid, ChaXunTiaoJian1 chaXunTiaoJian1, PageModel pageModel)
			throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=ThemeWorkMapper.countfindfindMyWork(chaXunTiaoJian1.getPrizeid(),userid, chaXunTiaoJian1.getCompetitionid(), chaXunTiaoJian1.getEnterpriseid(), chaXunTiaoJian1.getCode(), chaXunTiaoJian1.getWorkcode(), chaXunTiaoJian1.getPricel());
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = ThemeWorkMapper.findfindMyWork(chaXunTiaoJian1.getPrizeid(),userid, chaXunTiaoJian1.getCompetitionid(), chaXunTiaoJian1.getEnterpriseid(), chaXunTiaoJian1.getCode(), chaXunTiaoJian1.getWorkcode(), chaXunTiaoJian1.getPricel(), pageModel.getPage(), pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public PageResult<Map<String, Object>> findAllThemeWorkByCompetitionid(String title,String code,long competitionid, PageModel pageModel)
			throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=ThemeWorkMapper.countfindAllThemeWorkByCompetitionid(title,code,competitionid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = ThemeWorkMapper.findAllThemeWorkByCompetitionid(title,code,competitionid,pageModel.getPage(), pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public PageResult<Map<String, Object>> findAllThemeWorkByCompetitionidDivi(long divisionid, String title,
			String code, long competitionid, PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=ThemeWorkMapper.countfindAllThemeWorkByCompetitionidDivi(divisionid,title,code,competitionid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = ThemeWorkMapper.findAllThemeWorkByCompetitionidDivi(divisionid,title,code,competitionid,pageModel.getPage(), pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public PageResult<Map<String, Object>> findAllThemeWorkByCompetitionidJinJi(String title,
			String code, long competitionid, PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=ThemeWorkMapper.countfindAllThemeWorkByCompetitionidJinJi(title,code,competitionid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = ThemeWorkMapper.findAllThemeWorkByCompetitionidJinJi(title,code,competitionid,pageModel.getPage(), pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public List<Map<String,Object>> findThemeOverScore(long id) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = ThemeWorkMapper.findThemeOverScore(id);
		if(list!=null && list.size()>0)
		{
			List<String> ids = new ArrayList<String>();
			for (Map<String, Object> map : list) {
				ids.add(map.get("wjid").toString());
			}
			
			List<Map<String,Object>> list1 = ThemeWorkMapper.findThemeOverScore1(ids);
			for (Map<String, Object> map : list) {
				List<Map<String,Object>>  listt = new ArrayList<Map<String,Object>>();
				for (Map<String, Object> map2 : list1) {
					if(map.get("wjid").toString().equals(map2.get("id").toString()))
					{
						listt.add(map2);
					}
				}
				map.put("other", listt);
			}
			
			return list;
		}
		return  null;
	}

}
