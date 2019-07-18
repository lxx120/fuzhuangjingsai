package com.match.baciss.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.mapper.TeacherMapper;
import com.match.baciss.service.TeacherService;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private  TeacherMapper  TeacherMapper;
	
	@Override
	public PageResult<Map<String, Object>> findAllTeacher(String name, String phone, long collegeid, long departmentid,
			PageModel pageModel) throws Exception {
	
		PageResult<Map<String, Object>> rp = new PageResult<Map<String, Object>>();
		int count = TeacherMapper.countfindAllTeacher(name, phone, collegeid, departmentid);
		rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		rp.setRowCount(count);
		List<Map<String,Object>> list = TeacherMapper.findAllTeacher(name, phone, collegeid, departmentid, pageModel.getPage(),pageModel.getPagesize());
		rp.setItems(list);
		return rp;
	}

	@Override
	public PageResult<Map<String, Object>> findTeacherScoreList(long prizeid,long competitionid, String phone,PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String, Object>> rp = new PageResult<Map<String, Object>>();
		int count = TeacherMapper.countfindTeacherScoreList(prizeid, competitionid, phone);
		rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		rp.setRowCount(count);
		List<Map<String,Object>> list = TeacherMapper.findTeacherScoreList(prizeid, competitionid, phone,pageModel.getPage(),pageModel.getPagesize());
		for (Map<String, Object> map : list) {
			if(0==Integer.parseInt(map.get("score").toString()))
			{
				map.put("prizename", "未获奖");
			}
		}
		rp.setItems(list);
		return rp;
	}

}
