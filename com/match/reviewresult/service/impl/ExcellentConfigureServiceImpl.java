package com.match.reviewresult.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.reviewresult.mapper.ExcellentConfigureMapper;
import com.match.reviewresult.model.ExcellentConfigure;
import com.match.reviewresult.service.ExcellentConfigureService;

@Service
public class ExcellentConfigureServiceImpl implements ExcellentConfigureService {

	@Autowired
	private  ExcellentConfigureMapper  ExcellentConfigureMapper;
	
	@Override
	public Map<String, Object> findExcellentConfigure(long competitionid) throws Exception {
		// TODO Auto-generated method stub
		return ExcellentConfigureMapper.findExcellentConfigure(competitionid);
	}

	@Override
	public int addExcellentConfigure(ExcellentConfigure excellentConfigure) throws Exception {
		// TODO Auto-generated method stub
		excellentConfigure.setMtime(UserChangLiang.mtime());
		Map<String,Object>  map = ExcellentConfigureMapper.findIsExist(excellentConfigure.getCompetationid());
		if(map==null)
		{
			return ExcellentConfigureMapper.insertSelective(excellentConfigure);
		}
		return -1;
	}

	@Override
	public int updateExcellentConfigure(ExcellentConfigure excellentConfigure) throws Exception {
		// TODO Auto-generated method stub
		return ExcellentConfigureMapper.updateByPrimaryKeySelective(excellentConfigure);
	}

}
