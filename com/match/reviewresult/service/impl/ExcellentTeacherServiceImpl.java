package com.match.reviewresult.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.reviewresult.mapper.ExcellentConfigureMapper;
import com.match.reviewresult.mapper.ExcellentTeacherMapper;
import com.match.reviewresult.model.ExcellentTeacher;
import com.match.reviewresult.service.ExcellentTeacherService;

@Service
public class ExcellentTeacherServiceImpl implements ExcellentTeacherService {

	@Autowired
	private  ExcellentTeacherMapper  ExcellentTeacherMapper;
	
	@Autowired
	private  ExcellentConfigureMapper  ExcellentConfigureMapper;
	
	@Override
	public int addExcellentTeacher(ExcellentTeacher excellentTeacher) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map = ExcellentConfigureMapper.findExcellentConfigure(excellentTeacher.getCompetationid());
		if(map==null)
		{
			return -1;
		}
		int  person = Integer.parseInt(map.get("excellentteacher").toString());
		int  sum = ExcellentTeacherMapper.countSumTeacher(excellentTeacher.getCompetationid());
		if(person<sum)
		{
			return -2;
		}
		excellentTeacher.setType(1);
		excellentTeacher.setMtime(UserChangLiang.mtime());
		return ExcellentTeacherMapper.insertSelective(excellentTeacher);
	}

	@Override
	public int deleteExcellentTeacher(long id) throws Exception {
		// TODO Auto-generated method stub
		return ExcellentTeacherMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageResult<Map<String, Object>> findExcellentTeacherPage(PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String, Object>> rp = new PageResult<Map<String, Object>>();
		int count = ExcellentTeacherMapper.countfindExcellentTeacher();
		rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		rp.setRowCount(count);
		List<Map<String,Object>> list = ExcellentTeacherMapper.findExcellentTeacher(pageModel.getPage(), pageModel.getPagesize());
		rp.setItems(list);
		return rp;
	}

	@Override
	public PageResult<Map<String, Object>> findAllTeacherScorePage(long competitionid, long collegeid,
			PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String, Object>> rp = new PageResult<Map<String, Object>>();
		int count = ExcellentTeacherMapper.countfindAllTeacherScorePage(competitionid,collegeid);
		rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		rp.setRowCount(count);
		List<Map<String,Object>> list = ExcellentTeacherMapper.findAllTeacherScorePage(competitionid,collegeid,pageModel.getPage(), pageModel.getPagesize());
		rp.setItems(list);
		return rp;
	}

}
