package com.match.reviewresult.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.reviewresult.mapper.ExcellentPartmentMapper;
import com.match.reviewresult.service.ExcellentPartmentService;

@Service
public class ExcellentPartmentServiceImpl implements ExcellentPartmentService {

	@Autowired
	private  ExcellentPartmentMapper  ExcellentPartmentMapper;
	
	@Override
	public List<Map<String, Object>> findAllExcellentPartment(long competitionid, long collegeid) throws Exception {
		// TODO Auto-generated method stub
		return ExcellentPartmentMapper.findAllExcellentPartment(competitionid, collegeid);
	}

}
