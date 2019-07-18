package com.match.judges.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.judges.mapper.AssessDaiWorkMapper;
import com.match.judges.model.AssessDaiWork;
import com.match.judges.service.AssessDaiWorkService;
import com.match.proposition.mapper.CompetitionMapper;

@Service
public class AssessDaiWorkServiceImpl implements AssessDaiWorkService {

	@Autowired
	private  AssessDaiWorkMapper  AssessDaiWorkMapper;
	
	@Autowired
	private  CompetitionMapper  competitionMapper;
	
	@Override
	public List<Map<String, Object>> findAssessDaiWork(AssessDaiWork assessDaiWork) throws Exception {

		//获取当前的竞赛以及轮次
		Map<String,Object>  map = competitionMapper.findCurrentCompetition1(assessDaiWork.getDivisionid());
		if(map!=null)
		{
			assessDaiWork.setCompetitionid((long)Integer.parseInt(map.get("comid").toString()));
			assessDaiWork.setArid((long)Integer.parseInt(map.get("arid").toString()));
			return AssessDaiWorkMapper.findAssessDaiWorkList(assessDaiWork.getCompetitionid(),assessDaiWork.getArid(),assessDaiWork.getDivisionid());
		}
		return  null;
	}

}
