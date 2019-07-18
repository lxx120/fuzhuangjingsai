package com.match.proposition.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.DivisionCompanyMapper;
import com.match.proposition.model.DivisionCompany;
import com.match.proposition.service.DivisionCompanyService;

@Service
public class DivisionCompanyServiceImpl implements DivisionCompanyService {

	@Autowired
	private  DivisionCompanyMapper  DivisionCompanyMapper;
	
	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	
	@Override
	public PageResult<Map<String, Object>> findOthersCompanyPage(String province, String city, long divisionid,
			String name, PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map  =  CompetitionMapper.findCurrnetCompetition3();
		PageResult<Map<String,Object>>  pr = new PageResult<Map<String,Object>>();
		if(map!=null)
		{
			int count = DivisionCompanyMapper.countfindOthersCompanyPage(Long.parseLong(map.get("id").toString()),province, city, divisionid, name);
			pr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			pr.setRowCount(count);
			
			pr.setItems(DivisionCompanyMapper.findOthersCompanyPage(Long.parseLong(map.get("id").toString()),province, city, divisionid, name,pageModel.getPage() , pageModel.getPagesize()));
		}
		return pr;
	}

	@Override
	public PageResult<Map<String, Object>> findBangDingCompany(String province, String city, long divisionid,
			String name, PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map  =  CompetitionMapper.findCurrnetCompetition3();
		PageResult<Map<String,Object>>  pr = new PageResult<Map<String,Object>>();
		if(map!=null)
		{
			int count = DivisionCompanyMapper.countBangDingCompany(Long.parseLong(map.get("id").toString()),province, city, divisionid, name);
			pr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			pr.setRowCount(count);
			
			pr.setItems(DivisionCompanyMapper.findBangDingCompany(Long.parseLong(map.get("id").toString()),province, city, divisionid, name,pageModel.getPage() , pageModel.getPagesize()));
		}
		return pr;
	}

	@Override
	public boolean deleteDivisionCompany(long id) throws Exception {
		// TODO Auto-generated method stub
		int i = DivisionCompanyMapper.deleteByPrimaryKey(id);
		if(i>0)
		{
			return true;

		}
		return false;
	}

	@Override
	public int addBatchDivisionCompany(long divisionid, List<Map<String, Object>> list) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map  =  CompetitionMapper.findCurrnetCompetition3();
		if(map!=null)
		{
			for (Map<String, Object> map2 : list) {
				map2.put("competitionid",Long.parseLong(map.get("id").toString()));
				map2.put("divisionid", divisionid);
				map2.put("mtime", UserChangLiang.mtime());
			}
			int i = DivisionCompanyMapper.addBatchDivisionCompany(list);
			if(i>0)
			{
				return  1;
			}
			return -1;
		}
		return 0;
	}

	@Override
	public int addDivisionCompany(long competitionid,long divisionid, DivisionCompany deDivisionCompany) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map  =  CompetitionMapper.findCurrnetCompetition3();
		if(map!=null)
		{
			Map<String,Object> mmap = DivisionCompanyMapper.findExist(divisionid, deDivisionCompany.getCompanyid());
			if(mmap!=null)
			{
				return 2;
			}
			
			Map<String,Object>  mmp = DivisionCompanyMapper.findExistOtherDivision(Long.parseLong(map.get("id").toString()), deDivisionCompany.getCompanyid());
			if(mmp!=null)
			{
				return 3;
			}
			deDivisionCompany.setCompetitionid(Long.parseLong(map.get("id").toString()));
			deDivisionCompany.setDivisionid(divisionid);
			deDivisionCompany.setMtime(UserChangLiang.mtime());
			int i  = DivisionCompanyMapper.insertSelective(deDivisionCompany);
			if(i>0)
			{
				return  1;
			}
			return -1;
		}
		return 0;
	}

	
}
