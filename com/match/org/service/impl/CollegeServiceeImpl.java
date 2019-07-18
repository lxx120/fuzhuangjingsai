package com.match.org.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.match.baciss.cl.UserChangLiang;
import com.match.baciss.mapper.UserMapper;
import com.match.baciss.model.User;
import com.match.common.BasicMethod;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.org.mapper.CollegeMapper;
import com.match.org.model.College;
import com.match.org.service.CollegeService;
import com.match.pinyin.utils.Pinyinmethod;

@Service
public class CollegeServiceeImpl implements CollegeService {

	@Resource
	private  CollegeMapper  collegeMapper;
	
	@Resource
	private  UserMapper  usermapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, RuntimeException.class })
	public int addCollege(College college,String phone) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> usermap = usermapper.findUserExistByPhone(phone);
		if(usermap!=null)
		{ 
			return 2;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		college.setMtime(sdf.parse(sdf.format(new Date())));
		String [] a = college.getCity().split(",");
			college.setProvince(a[0]);
			college.setCity(a[1]);
		
		//添加学校
		collegeMapper.insertSelective(college);
		if(0!=college.getId())
		{
			//添加学校账号
			User user = new User();
			user.setEnterpriseid(college.getId());
			user.setPhone(phone);
			user.setRealname(college.getContator());
			String  s = Pinyinmethod.ToPinyin(college.getContator());
			s = s+System.currentTimeMillis();
			user.setUsername(s);
			user.setType(UserChangLiang.school);
			user.setWorker(1);
			user.setPassword(BasicMethod.encryptSHA1(UserChangLiang.password));
			user.setMtime(UserChangLiang.mtime());
			user.setManager(1);
			 usermapper.addUser(user);
			 return 1;
		}
		return -1;
	}

	@Override
	public int deleteCollege(long id) throws Exception {
		// TODO Auto-generated method stub
		return collegeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateCollege(long userid,College college) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map  = usermapper.findUserPhone(college.getPhone(), college.getId());
		if(map!=null)
		{
			return  2;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		college.setMtime(sdf.parse(sdf.format(new Date())));
		String [] a = college.getCity().split(",");
		college.setProvince(a[0]);
		college.setCity(a[1]);
		int i = collegeMapper.updateByPrimaryKeySelective(college);
		if(i>0)
		{
			//修改教师账号
			User user = new User();
			user.setEnterpriseid(college.getId());
			user.setPhone(college.getPhone());
			user.setId(userid);
			user.setRealname(college.getContator());
			String  s = Pinyinmethod.ToPinyin(college.getContator());
			s = s+System.currentTimeMillis();
			user.setUsername(s);
			user.setMtime(UserChangLiang.mtime());
			 usermapper.updateUser(user);
			return  1;
		}
		return -1;
	}

	@Override
	public PageResult<Map<String, Object>> findCollegePage(College college, PageModel pm) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		try {
			int count=collegeMapper.count(college);
			prr.setPages(Daomethod.countpages(count, pm.getPagesize()));
			prr.setRowCount(count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		List<Map<String,Object>> ml = collegeMapper.findCollegePage(college.getName(),college.getProvince(),college.getCity(), pm.getPage(),pm.getPagesize());
		
		prr.setItems(ml);
		return prr;
	}

	@Override
	public Map<String, Object> findCollegeById(long id) throws Exception {
		// TODO Auto-generated method stub
		
			List<Map<String,Object>>  list =  collegeMapper.findCollegeById(id);
			if(list!=null && list.size()>=1)
			{
				return list.get(0);
			}
			return  null;
	}

	@Override
	public List<Map<String, Object>> findCollegeList(String name) throws Exception {
		// TODO Auto-generated method stub
		return collegeMapper.findCollegeList(name);
	}

	@Override
	public PageResult<Map<String, Object>> findGoodCollegeListPage(College college, PageModel p) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=collegeMapper.counts(college);
		prr.setPages(Daomethod.countpages(count, p.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = collegeMapper.findGoodCollegeListPage(p.getPage(),p.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public boolean updateCollegeByID(College college) throws Exception {
		// TODO Auto-generated method stub
		int i  =  collegeMapper.updateByPrimaryKeySelective(college);
		if(i>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public PageResult<Map<String, Object>> findCollegeJoinCompetition(long competitionid, long collegeid,PageModel pageModel)
			throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=collegeMapper.countfindCollegeJoinCompetition(competitionid, collegeid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = collegeMapper.findCollegeJoinCompetition(competitionid,collegeid,pageModel.getPage(),pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public PageResult<Map<String, Object>> findCollegeTeacher(long competitionid, long enterpriseid,PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=collegeMapper.countfindCollegeTeacher(competitionid, enterpriseid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = collegeMapper.findCollegeTeacher(competitionid,enterpriseid,pageModel.getPage(),pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public PageResult<Map<String, Object>> findCollegeStudent(String jename,String major,long competitionid, long enterpriseid,
			PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=collegeMapper.countfindCollegeStudent(jename,major,competitionid, enterpriseid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = collegeMapper.findCollegeStudent(jename,major,competitionid,enterpriseid,pageModel.getPage(),pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public PageResult<Map<String, Object>> fingCollegeByDivision(String name, long competitionid, long divisionid,
			PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=collegeMapper.countfingCollegeByDivision(name,competitionid, divisionid);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = collegeMapper.fingCollegeByDivision(name,competitionid,divisionid,pageModel.getPage(),pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}


	


}
