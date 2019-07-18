package com.match.proposition.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.DivisionSchoolMapper;
import com.match.proposition.model.DivisionSchool;
import com.match.proposition.service.DivisionSchoolService;

@Service
public class DivisionSchoolServiceImpl implements DivisionSchoolService {

	@Resource
	private DivisionSchoolMapper  DivisionSchoolMapper;
	
	@Resource
	private  CompetitionMapper  CompetitionMapper;
	
	@Override
	public PageResult<Map<String, Object>> findOthersCollegePage(String province,String city, long divisionid, String name,
			PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map  =  CompetitionMapper.findCurrnetCompetition3();
		PageResult<Map<String,Object>>  pr = new PageResult<Map<String,Object>>();
		if(map!=null)
		{
			int count = DivisionSchoolMapper.countfindOthersCollegePage(Long.parseLong(map.get("id").toString()),province, city, divisionid, name);
			pr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			pr.setRowCount(count);
			
			pr.setItems(DivisionSchoolMapper.findOthersCollegePage(Long.parseLong(map.get("id").toString()),province, city, divisionid, name,pageModel.getPage() , pageModel.getPagesize()));
		}
		return pr;
	}

	@Override
	public PageResult<Map<String, Object>> findBangDingCollege(String province, String city, long divisionid,
			String name, PageModel pageModel)  throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map  =  CompetitionMapper.findCurrnetCompetition3();
		PageResult<Map<String,Object>>  pr = new PageResult<Map<String,Object>>();
		if(map!=null)
		{
			int count = DivisionSchoolMapper.countBangDingCollege(Long.parseLong(map.get("id").toString()),province, city, divisionid, name);
			pr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			pr.setRowCount(count);
			
			pr.setItems(DivisionSchoolMapper.findBangDingCollege(Long.parseLong(map.get("id").toString()),province, city, divisionid, name,pageModel.getPage() , pageModel.getPagesize()));
		}
		return pr;
	}

	@Override
	public boolean deleteDivisionSchool(long id) throws Exception {
		// TODO Auto-generated method stub
		int i = DivisionSchoolMapper.deleteByPrimaryKey(id);
		if(i>0)
		{
			return  true;
		}
		return false;
	}

	@Override
	public int addBatchDivisionSchool(long divisionid, List<Map<String, Object>> list) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String,Object>  map  =  CompetitionMapper.findCurrnetCompetition3();
		if(map!=null)
		{
			for (Map<String, Object> map2 : list) {
				map2.put("competitionid",Long.parseLong(map.get("id").toString()));
				map2.put("divisionid", divisionid);
				map2.put("mtime", UserChangLiang.mtime());
			}
			int i = DivisionSchoolMapper.addBatchDivisionSchool(list);
			if(i>0)
			{
				return  1;
			}
			return -1;
		}
		return 0;
	}

	@Override
	public int addDivisionSchool(long competitionid,long divisionid, DivisionSchool deDivisionSchool) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map  =  CompetitionMapper.findCurrnetCompetition3();
		if(map!=null)
		{
			Map<String,Object>  mapp = DivisionSchoolMapper.findExist(divisionid, deDivisionSchool.getSchoolid());
			if(mapp!=null)
			{
				return 2;
			}
			Map<String,Object>  mappp = DivisionSchoolMapper.findExistOtherDivision(Long.parseLong(map.get("id").toString()), deDivisionSchool.getSchoolid());
			if(mappp!=null)
			{
				return 3;
			}
			deDivisionSchool.setCompetitionid(Long.parseLong(map.get("id").toString()));
			deDivisionSchool.setMtime(UserChangLiang.mtime());
			deDivisionSchool.setDivisionid(divisionid);
			int i = DivisionSchoolMapper.insertSelective(deDivisionSchool);
			if(i>0)
			{
				return  1;
			}
			return -1;
		}
		return 0;
	}

}
