package com.match.proposition.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.model.Teacher;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.proposition.mapper.TeachersMapper;
import com.match.proposition.service.TeachersService;

@Service
public class TeachersServiceImpl implements TeachersService {

	@Autowired
	private TeachersMapper teachersMapper;

	@Override
	public PageResult<Map<String, Object>> findGoodTeacherPage(Teacher teacher, PageModel p) {
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count;
		try {
			count = teachersMapper.count(teacher);
			prr.setPages(Daomethod.countpages(count, p.getPagesize()));
			prr.setRowCount(count);
			List<Map<String,Object>> ml = teachersMapper.findGoodTeacherPage(p.getPage(),p.getPagesize());
			prr.setItems(ml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prr;
	}

	@Override
	public PageResult<Map<String, Object>> findTeacherTeam(long id, long competitionid, PageModel pageModel)
			throws Exception {
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count = teachersMapper.countfindTeacherTeam(id, competitionid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = teachersMapper.findTeacherTeam(id, competitionid, pageModel.getPage(), pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public List<Map<String, Object>> findTeamBuSignID(long id) throws Exception {
		// TODO Auto-generated method stub
		return teachersMapper.findTeamBuSignID(id);
		 
	}

	@Override
	public int updateTeamBuSign(List<Map<String, Object>> list) throws Exception {
		// TODO Auto-generated method stub
		int i = teachersMapper.updateTeamBuSign(list);
		if(i>0)
		{
			return 1;
		}
		return 0;
	}
	
}
