package com.match.proposition.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.proposition.mapper.CheatReportMapper;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.TeamMapper;
import com.match.proposition.mapper.ThemeMapper;
import com.match.proposition.mapper.ThemeSignupMapper;
import com.match.proposition.mapper.ThemeWorkFileMapper;
import com.match.proposition.mapper.ThemeWorkMapper;
import com.match.proposition.model.ThemeSignup;
import com.match.proposition.service.ThemeSignupService;

@Service
public class ThemeSignupServiceImpl implements ThemeSignupService {

	@Autowired
	private ThemeSignupMapper themeSignupMapper;

	@Autowired
	private CompetitionMapper CompetitionMapper;

	@Autowired
	private TeamMapper TeamMapper;

	@Autowired
	private ThemeMapper ThemeMapper;
	
	@Autowired
	private ThemeWorkFileMapper  ThemeWorkFileMapper;
	
	@Autowired
	private  CheatReportMapper CheatReportMapper;
	
	@Autowired
	private  ThemeWorkMapper  ThemeWorkMapper;

	@Override
	public int addThemeSignup(ThemeSignup themeSignup) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> thememap = ThemeMapper.findThemeInformatation(themeSignup.getThemeid());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 当前时间
		Date s1 = sdf.parse(sdf.format(new Date()));
		// 提交命题开始时间
		Date singupsttime = sdf.parse(thememap.get("singupsttime").toString());
		// 提交命题结束时间
		Date singupendtime = sdf.parse(thememap.get("singupendtime").toString());

		if (s1.compareTo(singupsttime) < 0) {
			return 5; // 还未到提交作品时间
		}
		if (s1.compareTo(singupendtime) > 0) {
			return 6; // 提交时间已过
		}

		themeSignup.setMtime(UserChangLiang.mtime());
		themeSignup.setSignuptime(UserChangLiang.mtime());
		themeSignup.setPostworks(1); // 未提交作品
		Map<String, Object> map = CompetitionMapper.findCurrnetCompetition2();
		themeSignup.setCompetitionid(Long.parseLong(map.get("id").toString()));
		themeSignup.setIsupload(1);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (Map<String, Object> map1 : themeSignup.getList()) {
			if (!map1.get("name").equals(""))
			{
				map1.put("workid", themeSignup.getId());
				map1.put("mtime", UserChangLiang.mtime());
				list.add(map1);
			}
		}
		if (themeSignup.getCompetitionType() == 2) {
			if (list.size() <= 1) {
				return 0; // 一个人不能为团队
			}
		}

		if (themeSignup.getCompetitionType() == 1) {
			if (list.size() == 1) {
				return 7; // 团队不能只有一个人
			}
		}

		int i1 = themeSignupMapper.insertSelective(themeSignup);
		if (i1 > 0) {
			for (Map<String, Object> map2 : list) {
				map2.put("workid", themeSignup.getId());
			}
			int i2 = TeamMapper.addBatchTeam(list);
			
			ThemeSignup themeSignup2 = new ThemeSignup();
			themeSignup2.setId(themeSignup.getId());
			Map<String, Object> codemap = themeSignupMapper.findAllCode(themeSignup.getId());
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

			themeSignup2.setStwcode(twcode);
			
			int i3 = themeSignupMapper.updateByPrimaryKeySelective(themeSignup2);
			
			if (i2 > 0 && i3>0) {
				return 1;
			}
			return -1;
		}
		
		return -1;

	}

	@Override
	public List<Map<String, Object>> findMyThemeSignup(long userid, long competitionid, String name) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = themeSignupMapper.findMyThemeSignup(userid, competitionid, name);
		return list;
	}

	@Override
	public int updateThemeSignup(ThemeSignup themeSignup) throws Exception {

		Map<String, Object> thememap = ThemeMapper.findThemeInformatation(themeSignup.getThemeid());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 当前时间
		Date s1 = sdf.parse(sdf.format(new Date()));
		// 提交命题开始时间
		Date singupsttime = sdf.parse(thememap.get("singupsttime").toString());
		// 提交命题结束时间
		Date singupendtime = sdf.parse(thememap.get("singupendtime").toString());

		if (s1.compareTo(singupsttime) < 0) {
			return 5; // 还未到提交作品时间
		}
		if (s1.compareTo(singupendtime) > 0) {
			return 6; // 提交时间已过
		}
		
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = themeSignup.getList();
		for (Map<String, Object> map1 : list) {
			if (!map1.get("name").equals(""))
			{
				map1.put("workid", themeSignup.getId());
				map1.put("mtime", UserChangLiang.mtime());
				list1.add(map1);
			}
		}
		if (themeSignup.getCompetitionType() == 2) {
			if (list1.size() <= 1) {
				return 0; // 团对参赛要填写多人信息
			}
		}

		if (themeSignup.getCompetitionType() == 1) {
			if (list1.size() != 1) {
				return 7; // 个人参赛只能一人参加
			}
		}
		// 团队
		themeSignup.setMtime(UserChangLiang.mtime());
		
		if (themeSignup.getCompetitionType() == 1 && list1.size() == 1) {
			int i = themeSignupMapper.updateByPrimaryKeySelective(themeSignup);
			if (i > 0) {
				TeamMapper.deleteTeamByWorkid(themeSignup.getId());
				int i1 = TeamMapper.addBatchTeam(list1);
				if (i1 > 0) {
					return 1;
				}
			}
		} else if (themeSignup.getCompetitionType() == 2 && list1.size() > 1) {
			themeSignupMapper.updateByPrimaryKeySelective(themeSignup);
			TeamMapper.deleteTeamByWorkid(themeSignup.getId());
			int i = TeamMapper.addBatchTeam(list1);
			if (i > 0) {
				return 1;
			}
		}

		return -1;
	}

	@Override
	public Map<String, Object> findThemeSignupInfo(long id) throws Exception {

		// 查询报名的报名详情
		Map<String, Object> map = themeSignupMapper.findThemeSignupInfo(id);
		map.put("team", TeamMapper.findTeamInfo(id));
		return map;
	}

	@Override
	public Map<String, Object> findInfoByThemesignId(long id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = themeSignupMapper.findInfoByThemesignId(id);
		if(map.get("id")!=null)
		{
			//查询上传的作品
			map.put("file", ThemeWorkFileMapper.findMyThemeWorkFile(Long.parseLong(map.get("id").toString())));
			//查询举报信息
			map.put("cheatreport", CheatReportMapper.findCheatByThemeid(Long.parseLong(map.get("id").toString())));
		}
		else
		{
			map.put("file", "");
			map.put("cheatreport", "");
		}
		return map;
	}

}
