package com.match.proposition.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.proposition.mapper.PropositionTypeMapper;
import com.match.proposition.service.PropositionTypeService;

@Service
public class PropositionTypeServiceImpl implements PropositionTypeService {

	@Autowired
	private  PropositionTypeMapper  PropositionTypeMapper;
	
	@Override
	public List<Map<String, Object>> findPropositionType() throws Exception {
		// TODO Auto-generated method stub
		return PropositionTypeMapper.findPropositionType();
	}

}
