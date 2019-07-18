package com.match.judges.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.judges.mapper.CompetitionAprizeMapper;
import com.match.judges.mapper.WinningPromotionMapper;
import com.match.judges.model.CompetitionAprize;
import com.match.judges.service.CompetitionAprizeService;
import com.match.proposition.mapper.CompetitionMapper;

@Service
public class CompetitionAprizeServiceImpl implements CompetitionAprizeService {

	@Autowired
	private  CompetitionAprizeMapper  CompetitionAprizeMapper;
	
	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	
	@Autowired
	private  WinningPromotionMapper  WinningPromotionMapper;
	
	@Override
	public boolean addCompetitionAprize(CompetitionAprize competitionAprize) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = CompetitionMapper.findCurrnetCompetition3();
		if(map!=null)
		{
			//添加配置
			competitionAprize.setCompetitionid(Long.parseLong(map.get("id").toString()));
			competitionAprize.setMtime(UserChangLiang.mtime());
			int i = CompetitionAprizeMapper.insertSelective(competitionAprize);
			
			
			if(i>0)
			{
				return   true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteCompetitionAprize(long id) throws Exception {
		// TODO Auto-generated method stub
		int i = CompetitionAprizeMapper.deleteByPrimaryKey(id);
		if(i>0)
		{
			return  true;
		}
		return false;
	}

	@Override
	public boolean updateCompetitionAprize(CompetitionAprize competitionAprize) throws Exception {
		// TODO Auto-generated method stub
		int i = CompetitionAprizeMapper.updateByPrimaryKeySelective(competitionAprize);
		if(i>0)
		{
			return  true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> findCompetitionAprize(long arid) throws Exception {
		// TODO Auto-generated method stub
		
		return CompetitionAprizeMapper.findCompetitionAprizeMapperMap(arid);
	}

	@Override
	public boolean addBatchCompetitionAprize(long divisionidid, List<Map<String, Object>> list) throws Exception {
		// TODO Auto-generated method stub
		for (Map<String, Object> map : list) {
			map.put("mtime", UserChangLiang.mtime());
			map.put("divisionidid", divisionidid);
		}
		int i = CompetitionAprizeMapper.addBatchCompetitionAprize(list);
		if(i>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> findPrizeList(long divisionid) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = CompetitionMapper.findCurrentCompetition1(divisionid);
		if(map!=null)
		{
			List<Map<String,Object>> list = CompetitionAprizeMapper.findfindPrizeList(Long.parseLong(map.get("arid").toString()), divisionid, Long.parseLong(map.get("comid").toString()));
			return  list;
		}
		return null;
	}

	@Override
	public Map<String, Object> findCompetitionAprizeById(long id) throws Exception {
		// TODO Auto-generated method stub
		return CompetitionAprizeMapper.findCompetitionAprizeById(id);
	}

	@Override
	public List<Map<String, Object>> findPrizeBYHuoJiang(long competitionid, long devisionid) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = CompetitionMapper.findCurrnetCompetition3();
		return CompetitionAprizeMapper.findPrizeBYHuoJiang(Long.parseLong(map.get("id").toString()), devisionid);
	}

	@Override
	public List<Map<String, Object>> findHuoJiangThemeWork(String code,long divisionid, long competitionid) throws Exception {
		// TODO Auto-generated method stub
		
		//先查询这个赛区的获奖配置
		List<Map<String,Object>>  list = WinningPromotionMapper.findHuoJiangThemeWork(code, divisionid, competitionid);
		return list;
	}

	@Override
	public List<Map<String, Object>> findCompetitionPrize(long enterpriseid, long competitionid, long divisionid)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = CompetitionAprizeMapper.findDivisionPrize(competitionid, divisionid);
		if(list==null || list.size()==0)
		{
			//查询总赛的评奖
			list = CompetitionAprizeMapper.findZongPrize(competitionid);
		}
		return list;
	}

}
