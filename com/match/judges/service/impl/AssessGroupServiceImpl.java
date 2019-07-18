package com.match.judges.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.judges.mapper.AssessGroupMapper;
import com.match.judges.model.CC;
import com.match.judges.service.AssessGroupService;

@Service
public class AssessGroupServiceImpl implements AssessGroupService {

	@Autowired
	private  AssessGroupMapper  AssessGroupMapper;
	
	@Override
	public List<Map<String, Object>> findAssessGroupList(long arrid) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  list = AssessGroupMapper.findAssessGroupList(arrid);
		return list;
	}

	@Override
	public CC findinfoByGroupId(long id) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = AssessGroupMapper.findinfoByGroupId(id);
		int groupnum = Integer.parseInt(map.get("groupnum").toString());  //分组个数
		int sumworks = Integer.parseInt(map.get("sumworks").toString());  //作品个数
		int sumteacher = Integer.parseInt(map.get("sumteacher").toString()); //老师个数
		
		int pagesize = sumworks/groupnum;
		int mo = sumworks%groupnum;
		
		CC cc = new CC();
		cc.setYushu(mo);
		cc.setShang(pagesize);
		cc.setGroupnum(groupnum);
		cc.setSumteacher(sumteacher);
		cc.setSumworks(sumworks);
		cc.setDivisionid(Long.parseLong(map.get("divisionid").toString()));
		cc.setCompetitionid(Long.parseLong(map.get("competitionid").toString()));
		cc.setPre(Long.parseLong(map.get("pre").toString()));
		cc.setAhid(Long.parseLong(map.get("ahid").toString()));
		cc.setLevel(Integer.parseInt(map.get("level").toString()));
		cc.setAhcode(map.get("ahcode").toString());
		cc.setMark(map.get("mark").toString());
		return cc;
	}

}
