package com.match.org.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.org.mapper.DepartMajorMapper;
import com.match.org.model.DepartMajor;
import com.match.org.service.DepartMajorService;

@Service
public class DepartMajorServiceImpl implements DepartMajorService {

	@Autowired
	private  DepartMajorMapper  DepartMajorMapper;
	
	@Override
	public List<Map<String, Object>> findDepartMajorList(DepartMajor departMajor) throws Exception {
		// TODO Auto-generated method stub
		return DepartMajorMapper.findDepartMajorList(departMajor);
	}

	@Override
	public boolean addDepartMajor(DepartMajor departMajor) throws Exception {
		// TODO Auto-generated method stub
		departMajor.setMtime(UserChangLiang.mtime());
		int i = DepartMajorMapper.insertSelective(departMajor);
		if(i>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean updateDepartMajor(DepartMajor departMajor) throws Exception {
		// TODO Auto-generated method stub
		departMajor.setMtime(UserChangLiang.mtime());
		int i  =  DepartMajorMapper.updateByPrimaryKeySelective(departMajor);
		if(i>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteDepartMajor(DepartMajor departMajor) throws Exception {
		// TODO Auto-generated method stub
		int i = DepartMajorMapper.deleteByPrimaryKey(departMajor.getId());
		if(i>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> findDepartMajorByid(long id) throws Exception {
		// TODO Auto-generated method stub
		return DepartMajorMapper.findDepartMajorByid(id);
	}

}
