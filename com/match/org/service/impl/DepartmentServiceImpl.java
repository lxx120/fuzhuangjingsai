package com.match.org.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.org.mapper.DepartmentMapper;
import com.match.org.model.Department;
import com.match.org.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private  DepartmentMapper  DepartmentMapper;
	
	@Override
	public List<Map<String, Object>> findDepartmentList(Department department) throws Exception {
		// TODO Auto-generated method stub
		return DepartmentMapper.findDepartmentList(department);
	}

	@Override
	public int addDepartment(Department department) throws Exception {
		// TODO Auto-generated method stub
		department.setMtime(UserChangLiang.mtime());
		return DepartmentMapper.insertSelective(department);
	}

	@Override
	public int updateDepartment(Department department) throws Exception {
		// TODO Auto-generated method stub
		department.setMtime(UserChangLiang.mtime());
		return DepartmentMapper.updateByPrimaryKeySelective(department);
	}

	@Override
	public int deleteDepartment(long id) throws Exception {
		// TODO Auto-generated method stub
		return DepartmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> findDepartmentById(long id) throws Exception {
		// TODO Auto-generated method stub
		return DepartmentMapper.findDepartmentById(id);
	}

}
