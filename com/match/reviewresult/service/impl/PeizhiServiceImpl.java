package com.match.reviewresult.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.reviewresult.mapper.PeizhiMapper;
import com.match.reviewresult.model.Peizhi;
import com.match.reviewresult.service.PeizhiService;

@Service
public class PeizhiServiceImpl implements PeizhiService {

	@Autowired
	private  PeizhiMapper  PeizhiMapper;
	
	@Override
	public int addPeizhi(Peizhi peizhi) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map = PeizhiMapper.findPeiZhi(peizhi.getType());
		int i = 0;
		if(map!=null)
		{
			 peizhi.setId(Long.parseLong(map.get("id").toString()));
			 i = PeizhiMapper.updatePeiZhi(peizhi);
		}
		else
		{
			i = PeizhiMapper.addPeiZhi(peizhi);
		}
		if(i>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public Map<String, Object> findPeizhi(int type) throws Exception {
		// TODO Auto-generated method stub
		return PeizhiMapper.findPeiZhi(type);
	}

}
