package com.match.judges.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.judges.cl.AssessHierarchyChangLiang;
import com.match.judges.mapper.GroupTeacherMapper;
import com.match.judges.mapper.WorkJudgeMapper;
import com.match.judges.model.CC;
import com.match.judges.model.GroupTeacher;
import com.match.judges.model.WorkJudge;
import com.match.judges.service.AssessGroupService;
import com.match.judges.service.GroupTeacherService;
import com.match.proposition.mapper.ThemeMapper;

@Service
public class GroupTeacherServiceImpl implements GroupTeacherService {

	@Resource
	private  GroupTeacherMapper  GroupTeacherMapper;
	
	@Autowired
	private  AssessGroupService  AssessGroupService;
	
	@Autowired
	private  ThemeMapper  ThemeMapper;
	
	@Autowired
	private  WorkJudgeMapper  WorkJudgeMapper;
	
	@Override
	public boolean addGroupTeacher(GroupTeacher groupTeacher) throws Exception {
		// TODO Auto-generated method stub
//		String [] a = groupTeacher.getJudhelist().split(",");
//		List list= Arrays.asList(a);
		List<Map<String,Object>> list = groupTeacher.getJudhelist();
		//添加老师
		List<Map<String,Object>>  list1 = new ArrayList<>();
		for (Map<String, Object> map1 : list) {
			Map<String,Object>  map = new HashMap<>();
			map.put("mtime", UserChangLiang.mtime());
			double s = (double) map1.get("teacherid");
			int s1 = (int)s;
			map.put("teacherid",(long)s1);
//			map.put("weight", Double.parseDouble(map.get("weight").toString()));
			map.put("groupid",groupTeacher.getGroupid());
			list1.add(map);
		}
		int i = GroupTeacherMapper.insertList(list1);
		if(i>0)
		{
			//分配评委
			CC cc = AssessGroupService.findinfoByGroupId(groupTeacher.getGroupid());
			
			//第几组
			int level = cc.getLevel();
			
			int k = 0;
			int k1 = 0;
			long arid = cc.getAhid();
			List<Map<String,Object>>  themelist = new ArrayList<Map<String,Object>>();
			
//			if(cc.getPre()==0 && cc.getAhcode().equals(AssessHierarchyChangLiang.AH002))
//			{
//				arid = 0;
//			}
			
			if(1==level)
			{
				k1 = cc.getShang()+cc.getYushu();
				themelist = ThemeMapper.findThemeDistributionPage(arid,cc.getDivisionid(), cc.getCompetitionid(), k, k1,groupTeacher.getCode());
			}
			else
			{
				k = (level-1)*cc.getShang()+cc.getYushu();
				themelist = ThemeMapper.findThemeDistributionPage(arid,cc.getDivisionid(), cc.getCompetitionid(), k, cc.getShang(),groupTeacher.getCode());
			}
			
			List<WorkJudge>  WorkJudgelist = new ArrayList<WorkJudge>();
			for (Map<String, Object> map1 : list) {
				for (Map<String, Object> map : themelist) {
					WorkJudge workJudge = new WorkJudge();
					workJudge.setArid(cc.getAhid());
					workJudge.setDivision(cc.getDivisionid());
					workJudge.setWorksid(Long.parseLong(map.get("workid").toString()));
					double s = (double) map1.get("teacherid");
					int s1 = (int)s;
					workJudge.setJudgeid((long)s1);
					workJudge.setGroupid(groupTeacher.getGroupid());
					workJudge.setJudged(-1);
					workJudge.setMtime(UserChangLiang.mtime());
					workJudge.setCompetitionid(cc.getCompetitionid());
//					workJudge.setWeight(Double.parseDouble(map1.get("weight").toString()));
					WorkJudgelist.add(workJudge);
				}
			}
			WorkJudgeMapper.addBatchWorkJudge(WorkJudgelist);
 			return  true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> findGroupTeacherList(long groupid) throws Exception {
		// TODO Auto-generated method stub
		return GroupTeacherMapper.findGroupTeacherList(groupid);
	}

	@Override
	public boolean deleteGroupTeacher(long id,long divisionid,long groupid,long arid,long teacherid) throws Exception {
		
		//删除选定组的某个老师
		int i = GroupTeacherMapper.deleteByPrimaryKey(id);
		if(i>0)
		{
			//删除老师分配的作品
			int j = WorkJudgeMapper.deleteWorkJudgeByTeacher(teacherid, divisionid, arid,groupid);
			
			//修改老师的权重
			int z  = GroupTeacherMapper.updateGroupTeacherWeight(groupid);
			
			if(j>0 )
			{
				return  true;
			}
		}
		return false;
	}

	@Override
	public boolean updateBatchGroupTeacher(List<Map<String, Object>> list) throws Exception {
		// TODO Auto-generated method stub
		List<GroupTeacher>  list1 = new ArrayList<GroupTeacher>();
		for (Map map : list) {
			//修改教师权重
			GroupTeacher groupTeacher = new GroupTeacher();
			double s = (double) map.get("id");
			int s1 = (int)s;
 			groupTeacher.setId((long)s1);
 			
 			double s2 = (double) map.get("weight");
			groupTeacher.setWeight(s2);
			list1.add(groupTeacher);
			GroupTeacherMapper.updateByPrimaryKeySelective(groupTeacher);
			
			//查询这个组老师所分配的作品
			List<Map<String,Object>>  listj1 = new ArrayList<Map<String,Object>>();
			double sg = (double) map.get("groupid");
			int sg1 = (int)sg;
			double st = (double) map.get("teacherid");
			int st1 = (int)st;
			List<Map<String,Object>> listj = WorkJudgeMapper.findIdByGTid((long)sg1,(long)st1);
			for (Map<String,Object> mapj1 : listj) {
				Map<String,Object>  mapj = new HashMap<String, Object>();
				mapj.put("weight", s2);
				mapj.put("id",Long.parseLong(mapj1.get("id").toString()));
				listj1.add(mapj);
			}
			
			WorkJudgeMapper.updateBatch(listj1);
			
		}
		if(1>0)
		{
			return  true;
		}
		return false;
	}

}
