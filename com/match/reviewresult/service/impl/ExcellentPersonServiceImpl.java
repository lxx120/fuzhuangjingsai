package com.match.reviewresult.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.reviewresult.mapper.ExcellentPersonMapper;
import com.match.reviewresult.service.ExcellentPersonService;

@Service
public class ExcellentPersonServiceImpl implements ExcellentPersonService {

	@Autowired
	private  ExcellentPersonMapper  ExcellentPersonMapper;
	
	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	
	@Override
	public PageResult<Map<String, Object>> findExcellentPersonPage(PageModel pageModel)
			throws Exception {
		PageResult<Map<String, Object>> rp = new PageResult<Map<String, Object>>();
		Map<String,Object> map = CompetitionMapper.findCurrnetCompetition4();
		if(map==null)
		{
			rp.setItems(null);
			return rp;
		}
		int count = ExcellentPersonMapper.countExcellentPersonPage(Long.parseLong(map.get("id").toString()));
		rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		rp.setRowCount(count);
		List<Map<String,Object>> list = ExcellentPersonMapper.findExcellentPersonPage(Long.parseLong(map.get("id").toString()),pageModel.getPage(), pageModel.getPagesize());
		rp.setItems(list);
		return rp;
	}

}
