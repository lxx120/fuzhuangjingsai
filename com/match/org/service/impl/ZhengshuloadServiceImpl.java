package com.match.org.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.org.mapper.ZhengshuloadMapper;
import com.match.org.model.Zhengshuload;
import com.match.org.service.ZhengshuloadService;

@Service
public class ZhengshuloadServiceImpl implements ZhengshuloadService {

	@Autowired
	private  ZhengshuloadMapper  ZhengshuloadMapper;
	
	@Override
	public int addZhengshu(Zhengshuload zhengshuload) throws Exception {
		// TODO Auto-generated method stub
		return ZhengshuloadMapper.addZhengshu(zhengshuload);
	}

	@Override
	public int updateZhengshu(Zhengshuload zhengshuload) throws Exception {
		// TODO Auto-generated method stub
		return ZhengshuloadMapper.addZhengshu(zhengshuload);
	}

	@Override
	public Map<String, Object> finfzhengshu(long id) throws Exception {
		// TODO Auto-generated method stub
		return ZhengshuloadMapper.finfzhengshu(id);
	}

	@Override
	public List<Map<String, Object>> findzhengshulist(long competitionid) throws Exception {
		// TODO Auto-generated method stub
		return ZhengshuloadMapper.findzhengshulist(competitionid);
	}

}
