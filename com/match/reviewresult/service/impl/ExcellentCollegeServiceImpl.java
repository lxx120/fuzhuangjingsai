package com.match.reviewresult.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.reviewresult.mapper.ExcellentCollegeMapper;
import com.match.reviewresult.service.ExcellentCollegeService;

@Service
public class ExcellentCollegeServiceImpl implements ExcellentCollegeService {

	@Autowired
	private  ExcellentCollegeMapper  ExcellentCollegeMapper;
	
	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	
	@Override
	public PageResult<Map<String, Object>> findExcellentCollege(PageModel pageModel)
			throws Exception {
		// TODO Auto-generated method stub
			PageResult<Map<String, Object>> rp = new PageResult<Map<String, Object>>();
			Map<String,Object> map = CompetitionMapper.findCurrnetCompetition4();
			if(map==null)
			{
				rp.setItems(null);
				return rp;
			}
			int count = ExcellentCollegeMapper.countfindAllCollegePage(Long.parseLong(map.get("id").toString()));
			rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			rp.setRowCount(count);
			List<Map<String,Object>> list = ExcellentCollegeMapper.findAllCollegePage(Long.parseLong(map.get("id").toString()),pageModel.getPage(), pageModel.getPagesize());
			rp.setItems(list);
			return rp;
	}

	@Override
	public List<Map<String, Object>> findExcellentCollege1() throws Exception {
		// TODO Auto-generated method stub
		//查询上一届的学校
		Map<String,Object> map = CompetitionMapper.findCurrnetCompetition4();
		if(map==null)
		{
			return  null;
		}
		return ExcellentCollegeMapper.findExcellentCollege(Long.parseLong(map.get("id").toString()));
	}

}
