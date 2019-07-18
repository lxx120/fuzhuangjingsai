package com.match.org.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.org.mapper.propositionstrategyMapper;
import com.match.org.model.propositionstrategy;
import com.match.org.service.PropositionstrategyService;
import com.match.proposition.mapper.CompetitionMapper;

@Service
public class PropositionstrategyServiceImpl implements PropositionstrategyService {

	@Resource
	private  propositionstrategyMapper propositionstrategyMapper;
	
	@Resource
	private  CompetitionMapper  CompetitionMapper;
	
	@Override
	public boolean addPropositionstrategy(propositionstrategy propositionstrategy) throws Exception {
		// TODO Auto-generated method stub
		propositionstrategy.setMtime(UserChangLiang.mtime());
//		Map<String,Object>  map = CompetitionMapper.findCurrnetCompetition2();
//		if(map!=null)
//		{
//			propositionstrategy.setCompetitionid(Long.parseLong(map.get("id").toString()));
//		}
		int i =  propositionstrategyMapper.addPropositionstrategy(propositionstrategy);
		if(i>0)
		{
			return  true;
		}
		return  false;
	}

	@Override
	public boolean updatePropositionstrategy(propositionstrategy propositionstrategy) throws Exception {
		// TODO Auto-generated method stub
		propositionstrategy.setMtime(UserChangLiang.mtime());
		int i = propositionstrategyMapper.updatePropositionstrategy(propositionstrategy);
		if(i>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> findPropositionstrategy(long id) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map = CompetitionMapper.findCurrnetCompetition2();
		if(map!=null)
		{
			return propositionstrategyMapper.findPropositionstrategy(id);
		}
		return null;
	}

}
