package com.match.judges.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.judges.mapper.AssessGroupMapper;
import com.match.judges.mapper.AssessRoleMapper;
import com.match.judges.model.AssessRole;
import com.match.judges.service.AssessRoleService;

@Service
public class AssessRoleServiceImpl implements AssessRoleService {

	@Resource
	private AssessRoleMapper AssessRoleMapper;

	@Autowired
	private AssessGroupMapper AssessGroupMapper;

	@Override
	public boolean addAssessRole(AssessRole assessRole) throws Exception {

		// 添加评审分配
		assessRole.setMtime(UserChangLiang.mtime());
		int i = AssessRoleMapper.insertSelective(assessRole);
		if (i > 0) {
			List<Map<String, Object>> list = new ArrayList<>();
			// 根据组数去添加分组表
			for (int j = 0; j < assessRole.getGroupnum(); j++) {
				Map<String, Object> map = new HashMap<>();
				map.put("arrid", assessRole.getId());
				map.put("name", "第" + (j + 1) + "组");
				map.put("code", "AG" + (j + 1));
				map.put("mtime", UserChangLiang.mtime());
				map.put("level", j+1);
				list.add(map);
			}

			int i1 = AssessGroupMapper.insetList(list);
			if (i1 > 0) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean updateAssessRole(AssessRole assessRole) throws Exception {
		
		//先删除
		AssessRoleMapper.deleteAssessRole(assessRole);
		// 添加评审分配
		assessRole.setMtime(UserChangLiang.mtime());
		int i = AssessRoleMapper.insertSelective(assessRole);
		if (i > 0) {
			List<Map<String, Object>> list = new ArrayList<>();
			// 根据组数去添加分组表
			for (int j = 0; j < assessRole.getGroupnum(); j++) {
				Map<String, Object> map = new HashMap<>();
				map.put("arrid", assessRole.getId());
				map.put("name", "第" + (j + 1) + "组");
				map.put("code", "AG" + (j + 1));
				map.put("mtime", UserChangLiang.mtime());
				map.put("level", j+1);
				list.add(map);
			}

			int i1 = AssessGroupMapper.insetList(list);
			if (i1 > 0) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean findAssessRole(AssessRole assessRole) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
