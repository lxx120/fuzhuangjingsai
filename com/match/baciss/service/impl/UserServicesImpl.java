package com.match.baciss.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.baciss.mapper.StudentMapper;
import com.match.baciss.mapper.TeacherMapper;
import com.match.baciss.mapper.UserMapper;
import com.match.baciss.mapper.UserRoleMapper;
import com.match.baciss.mapper.WorkerMapper;
import com.match.baciss.model.LoginMessage;
import com.match.baciss.model.Student;
import com.match.baciss.model.Teacher;
import com.match.baciss.model.User;
import com.match.baciss.model.UserRole;
import com.match.baciss.model.Worker;
import com.match.baciss.service.UserServices;
import com.match.common.BasicMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.org.mapper.CollegeMapper;
import com.match.org.mapper.EnterpriseMapper;
import com.match.org.model.Enterprise;
import com.match.pinyin.utils.Pinyinmethod;
import com.match.proposition.mapper.CompetitionMapper;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private  UserMapper  userMapper;
	
	@Autowired
	private  UserRoleMapper  userRoleMapper;
	
	@Autowired
	private  TeacherMapper  TeacherMapper;
	
	@Autowired
	private  StudentMapper  StudentMapper;
	
	@Autowired
	private  EnterpriseMapper EnterpriseMapper;
	
	@Autowired
	private  WorkerMapper  WorkerMapper;
	
	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	
	@Autowired
	private  CollegeMapper  CollegeMapper;
	
	@Override
	public ObjectResult<Map<String, Object>> deleteUser(Long userid) {
		// TODO Auto-generated method stub
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		try {
			userMapper.deleteByPrimaryKey(userid);
			orr.setCode(0);
			orr.setErrmsg("删除成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orr;
	}

	@Override
	public ObjectResult<Map<String, Object>> findmemeber(Long userid) {
		// TODO Auto-generated method stub
		ObjectResult<Map<String,Object>>  orr = new ObjectResult<Map<String, Object>>();
		if(userid==null || userid.equals(""))
		{
			orr.setCode(1);
			orr.setErrmsg("用户ID不能为空");
		}
		else
		{
			try {
				
				Map<String,Object>  map = userMapper.findmember(userid);
				if(map!=null)
				{
					orr.setCode(0);
					orr.setErrmsg("查询成功");
					orr.setItem(map);
				}
				else
				{
					orr.setCode(1);
					orr.setErrmsg("查询失败");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return orr;
	}

	@Override
	public PageResult<Map<String, Object>> findmemberpage(User user, PageModel p) {
		// TODO Auto-generated method stub
			PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
			try {
				int count=userMapper.count(user);
				prr.setPages(Daomethod.countpages(count, p.getPagesize()));
				prr.setRowCount(count);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			List<Map<String,Object>> ml = userMapper.findmemberpage(user.getRealname(),user.getPhone(),p.getPage(), p.getPagesize());
			
			prr.setItems(ml);
			return prr;
	}

	@Override
	public int updateUser(User user,long roleid) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setMtime(sdf.parse(sdf.format(new Date())));
		int i =  userMapper.updateUser(user);
		if(i>0)
		{
			UserRole userRole = new UserRole();
			userRole.setRoleid(roleid);
			userRole.setUserid(user.getId());
			
			return  userRoleMapper.updateRoleUserByUserid(userRole);
		}
		return  0;
	}

	@Override
	public ObjectResult<LoginMessage> dologin(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		ObjectResult<LoginMessage>  obj = new ObjectResult<LoginMessage>();
		if(StringUtils.isBlank(username))
		{
			obj.setCode(1);
			obj.setErrmsg("账号不能为空");
			return  obj;
		}
		if(StringUtils.isBlank(password))
		{
			obj.setCode(1);
			obj.setErrmsg("密码不能为空");
			return obj;
		}
		Map<String,Object>  map = userMapper.findUserToPassword(username);
		if(map!=null && map.size()>0)
		{
			if(BasicMethod.encryptSHA1(password).equals(map.get("password").toString()))
			{
				List<Map<String,Object>>  list  =  userMapper.findUserMessage(username);
			
				Map<String,Object>  mapcom = CompetitionMapper.findCurrnetCompetition3();

				if(list!=null && list.size()==1)
				{
					LoginMessage loginMessage = new LoginMessage();
					BeanUtils.populate(loginMessage, list.get(0));
					if(mapcom!=null)
					{
						loginMessage.setComname(mapcom.get("name").toString());
						loginMessage.setCompetitionid(Long.parseLong(mapcom.get("id").toString()));
					}
					if(0==loginMessage.getDivisionid())
					{
						loginMessage.setDivisionid(0);
					}
					obj.setItem(loginMessage);
					obj.setCode(0);
					return  obj;
				}
				if(list!=null && list.size()>1)
				{
					for (Map<String, Object> map2 : list) {
						if(1==Integer.parseInt(map2.get("status").toString()))
						{
							LoginMessage loginMessage = new LoginMessage();
							BeanUtils.populate(loginMessage, map2);
							if(mapcom!=null)
							{
								loginMessage.setComname(mapcom.get("name").toString());
								loginMessage.setCompetitionid(Long.parseLong(mapcom.get("id").toString()));
							}
							if(0==loginMessage.getDivisionid())
							{
								loginMessage.setDivisionid(0);
							}
							obj.setItem(loginMessage);
							obj.setCode(0);
							return  obj;
						}
					}
					
					LoginMessage loginMessage = new LoginMessage();
					BeanUtils.populate(loginMessage, list.get(0));
					obj.setItem(loginMessage);
					obj.setCode(0);
					return  obj;
				}
			}
			else
			{
				obj.setCode(1);
				obj.setErrmsg("密码不正确");
			}
		}
		else
		{
			obj.setCode(1);
			obj.setErrmsg("账号不存在");
		}
		
		return obj;
	}

	@Override
	public int addUser(User user,long roleid) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String,Object>  mapuser = userMapper.findUserExist(user.getUsername());
		if(mapuser!= null && mapuser.size()>0)
		{
				return -1;
		}
		else
		{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			user.setMtime(sdf.parse(sdf.format(new Date())));
			if(1==roleid)
			{
				user.setType(3);
			}
			else if(2==roleid)
			{
				user.setType(4);
			}
			userMapper.addUser(user);
			if(user.getId()!=0 && roleid!=0)
			{
				UserRole userRole = new UserRole();
				userRole.setMtime(sdf.parse(sdf.format(new Date())));
				userRole.setUserid(user.getId());
				userRole.setRoleid(roleid);
				int i = userRoleMapper.insert(userRole);
				if(i>0)
				{
					return 1;
				}
				return  0;
			}
			else
			{
				return 1;
			}
		}
	}

	@Override
	public int adduserByPhone(User user) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  map = userMapper.findUserExistByPhone(user.getPhone());
		if(map!=null && map.size()>0)
		{
				return 2;
		}
		else
		{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			user.setMtime(sdf.parse(sdf.format(new Date())));
			userMapper.addUser(user);
		}
		return 1;
	}

	@Override
	public ObjectResult<LoginMessage> dologinWeb(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		ObjectResult<LoginMessage>  obj = new ObjectResult<LoginMessage>();
		if(StringUtils.isBlank(username))
		{
			obj.setCode(1);
			obj.setErrmsg("账号不能为空");
			return  obj;
		}
		if(StringUtils.isBlank(password))
		{
			obj.setCode(1);
			obj.setErrmsg("密码不能为空");
			return obj;
		}
		
		//判断传来的账号是用户名还是密码
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        boolean flag =  pattern.matcher(username).matches(); 
        Map<String,Object>  map = new HashMap<String, Object>();
        //手机号
        if(flag)
        {
        	map = userMapper.findUserToPasswordByPhone(username);
        }
        else
        {
        	map = userMapper.findUserToPassword(username);
        }
		
        LoginMessage loginMessage = new LoginMessage();
		loginMessage.setDivisionid((long)-1);
		loginMessage.setEnterpriseid((long)-1);
		if(map!=null && map.size()>0)
		{
			if(BasicMethod.encryptSHA1(password).equals(map.get("password").toString()))
			{
				//查询当前的比赛
				Map<String,Object> commap = CompetitionMapper.findCurrnetCompetition3();
				if(commap==null)
				{
					obj.setCode(1);
					obj.setErrmsg("当前无比赛");
					return obj;
				}
				//用户信息
				Map<String,Object> usemap = userMapper.findUserMessageWeb(Long.parseLong(map.get("id").toString()));
				if(usemap!=null)
				{
					if(usemap.get("judgeid")!=null)
					{
						//1是评委  0不是评委
						loginMessage.setJudgeid(Long.parseLong(usemap.get("judgeid").toString()));
						loginMessage.setIsjudge(1);
					}
					else
					{
						
						loginMessage.setIsjudge(Integer.parseInt(usemap.get("isjudge").toString()));
						loginMessage.setJudgeid((long) 0);
					}

				}
				else
				{
					loginMessage.setIsjudge(0);
					loginMessage.setJudgeid((long) 0);
				}
				Map<String,Object> dmap = new HashMap<String, Object>();
				//企业人员
				if(1==Integer.parseInt(usemap.get("isperfect").toString()))
				{
					if(1==Integer.parseInt(usemap.get("type").toString()))
					{
						dmap = CompetitionMapper.findUserInfoCompany(Long.parseLong(usemap.get("enterpriseid").toString()), Long.parseLong(commap.get("id").toString()));
					}
					else
					{
						dmap = CompetitionMapper.findUserInfoSchool(Long.parseLong(usemap.get("enterpriseid").toString()), Long.parseLong(commap.get("id").toString()));
					}
					if(dmap!=null)
					{
						loginMessage.setDivisionid(Long.parseLong(dmap.get("divisionid").toString()));
					}
					loginMessage.setEnterpriseid(Long.parseLong(usemap.get("enterpriseid").toString()));
				}
				
				loginMessage.setCompetitionid(Long.parseLong(commap.get("id").toString()));
				loginMessage.setId(Long.parseLong(usemap.get("id").toString()));
				loginMessage.setIsperfect(Integer.parseInt(usemap.get("isperfect").toString()));
				loginMessage.setRealname(usemap.get("realName").toString());
				loginMessage.setUsertype(Integer.parseInt(usemap.get("type").toString()));
				loginMessage.setComname(commap.get("name").toString());
				loginMessage.setManager(Integer.parseInt(usemap.get("manager").toString()));
				loginMessage.setLeader(Integer.parseInt(usemap.get("leader").toString()));
				loginMessage.setPhone(usemap.get("phone").toString());
				obj.setItem(loginMessage);
				obj.setCode(0);
			}
			else
			{
				obj.setCode(1);
				obj.setErrmsg("密码不正确");
			}
		}
		else
		{
			obj.setCode(1);
			obj.setErrmsg("账号不存在");
		}
		
		return obj;
	}

	@Override
	public int updatePassword(long id, String oldpassword,String password, String sepassword) throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(password))
		{
			return 2;  //密码没有输入
		}
		if(StringUtils.isBlank(sepassword))
		{
			return 3;  //第二次密码没有输入
		}
		if(!password.equals(sepassword))
		{
			return  4;   // 两次输入密码不同
		}
		
		Map<String,Object> map = userMapper.findUserToPasswordById(id);
		if(map!=null)
		{
			if(BasicMethod.encryptSHA1(password).equals(map.get("password").toString()))
			{
				return 5;  //新密码与原密码一致
			}
			if(!BasicMethod.encryptSHA1(oldpassword).equals(map.get("password").toString()))
			{
				return 7; //原始密码不正确
			}
		}
		User user = new User();
		user.setId(Long.parseLong(map.get("id").toString()));
		user.setPassword(BasicMethod.encryptSHA1(password));
		int i = userMapper.updateUser(user);
		if(i>0)
		{
			return 1;
		}
		return -1;
	}

	@Override
	public int updateTeacherPerfect(Teacher teacher, User user) throws Exception {
		
		Map<String,Object> map = TeacherMapper.findTeacherinformation(user.getId());
		teacher.setUserid(user.getId());
		teacher.setMtime(UserChangLiang.mtime());
		user.setMtime(UserChangLiang.mtime());
		user.setIsperfect(1);
		int i =1;
		if(map!=null)
		{
			 i = TeacherMapper.updateByPrimaryKeySelective(teacher);
		}
		else
		{
			 i = TeacherMapper.insertSelective(teacher);
		}
		if(StringUtils.isNotBlank(user.getUsername()))
		{
			Map map1 = userMapper.findUserUsernameIsExists(user.getUsername());
			if(map1!=null)
			{
				return 2;
			}
		}
		
		int i1 = userMapper.updateUser(user);
		if(i>0 && i1>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public Map<String, Object> findTeacherInformatation(long id) throws Exception {
		// TODO Auto-generated method stub
		
		return userMapper.findTeacherInformatation(id);
	}

	@Override
	public Map<String, Object> findTeacherInfo(long id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.findTeacherInfo(id);
	}

	@Override
	public int updateUserPhoto(User user) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.updateUser(user);
	}

	@Override
	public Map<String, Object> findStudentInfomatation(long id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.findStudentInfomatation(id);
	}

	@Override
	public int updateStudentPerfect(Student student, User user) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = StudentMapper.findStudentinformation(user.getId());
		student.setUserid(user.getId());
		student.setMtime(UserChangLiang.mtime());
		user.setMtime(UserChangLiang.mtime());
		user.setIsperfect(1);
		int i =1;
		if(map!=null)
		{
			 student.setId(Long.parseLong(map.get("id").toString()));
			 i = StudentMapper.updateByPrimaryKeySelective(student);
		}
		else
		{
			 i = StudentMapper.insertSelective(student);
		}
		if(StringUtils.isNotBlank(user.getUsername()))
		{
			Map map1 = userMapper.findUserUsernameIsExists(user.getUsername());
			if(map1!=null)
			{
				return 2;
			}
		}
		
		int i1 = userMapper.updateUser(user);
		if(i>0 && i1>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public Map<String, Object> findEnterpriseInformation(long id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.findEnterpriseInformation(id);
	}

	@Override
	public int updateEnterprisePerfect(User user) throws Exception {
		// TODO Auto-generated method stub
//		exEnterprise.setId(user.getEnterpriseid());
		user.setMtime(UserChangLiang.mtime());
//		exEnterprise.setMtime(UserChangLiang.mtime());
		user.setIsperfect(1);
		int i = userMapper.updateUser(user);
		if(user.getType()==5)
		{
			Student student = new Student();
			student.setUserid(user.getId());
			student.setCollege(user.getEnterpriseid());
			StudentMapper.updateStudentByuserid(student);
		}
//		int i1 = EnterpriseMapper.updateByPrimaryKeySelective(exEnterprise);
		if(i>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public int AddStudentStaff(User user) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  mapphone = userMapper.findUserExistByPhone(user.getPhone());
		if(mapphone!=null)
		{
			return 2; //手机号已经存在
		}
//		String s = Pinyinmethod.ToPinyin(user.getRealname());
//		s = s + System.currentTimeMillis();
		String s =  UUID.randomUUID().toString().replaceAll("-", "");
		user.setUsername(s);
		user.setRealname(s);
		user.setMtime(UserChangLiang.mtime());
		user.setType(UserChangLiang.student);
		user.setPassword(BasicMethod.encryptSHA1(UserChangLiang.password));
		userMapper.addUser(user);
		
		Student student = new Student();
		student.setUserid(user.getId());
		student.setMtime(UserChangLiang.mtime());
		StudentMapper.insertSelective(student);
		
//		teacher.setUserid(user.getId());
//		
//		int i = TeacherMapper.insertSelective(teacher);
		if(1>0)
		{
			return 1;
		}
		
		return 0;
	}

	@Override
	public int AddCompanyStaff(User user, Worker worker) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  mapphone = userMapper.findUserExistByPhone(user.getPhone());
		if(mapphone!=null)
		{
			return 2; //手机号已经存在
		}
		String s = Pinyinmethod.ToPinyin(user.getRealname());
		s = s + System.currentTimeMillis();
		user.setUsername(s);
		user.setMtime(UserChangLiang.mtime());
		user.setType(UserChangLiang.companystaff);
		user.setPassword(BasicMethod.encryptSHA1(UserChangLiang.password));
		user.setMtime(UserChangLiang.mtime());
		userMapper.addUser(user);
		
		worker.setEnterprise(user.getEnterpriseid());
		worker.setUserid(user.getId());
		worker.setMtime(UserChangLiang.mtime());
		
		int i = WorkerMapper.insertSelective(worker);
		if(i>0)
		{
			return 1;
		}
		
		return 0;
	}

	@Override
	public Map<String, Object> finduserInfo(long id) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map =  userMapper.finduserInfo(id);
		if(map!=null)
		{
			int type = Integer.parseInt(map.get("type").toString());
			if(type==5)
			{
				if(map.get("enterpriseid")!=null)
				{
					
//					Map<String,Object>  comap = CollegeMapper.findCollegeInfo(Long.parseLong(map.get("enterpriseid").toString()));
					Map<String,Object>  comap = StudentMapper.findStudent(id);
					if(comap!=null)
					{
						map.putAll(comap);
					}
				}
				else
				{
					map.put("collegeid", "");
					map.put("collegename", "");
					map.put("departmentid", "");
					map.put("dename", "");
					map.put("majorid", "");
					map.put("major", "");
				}
			}
		}
		return map;
	}

	@Override
	public int addSchoolStaff(User user, Teacher teacher) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  mapphone = userMapper.findUserExistByPhone(user.getPhone());
		if(mapphone!=null)
		{
			return 2; //手机号已经存在
		}
		String s = Pinyinmethod.ToPinyin(user.getRealname());
		s = s + System.currentTimeMillis();
		user.setUsername(s);
		user.setMtime(UserChangLiang.mtime());
		user.setType(UserChangLiang.teacher);
		user.setPassword(BasicMethod.encryptSHA1(UserChangLiang.password));
		user.setMtime(UserChangLiang.mtime());
		userMapper.addUser(user);
		
		teacher.setCollege(user.getEnterpriseid());
		teacher.setUserid(user.getId());
		teacher.setMtime(UserChangLiang.mtime());
		
		int i = TeacherMapper.insertSelective(teacher);
		
		if(i>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public PageResult<Map<String, Object>> findSchoolStaff(long enterpriseid,String realname, PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
			int count=userMapper.countfindSchoolStaff(enterpriseid,realname);
			prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			prr.setRowCount(count);
		List<Map<String,Object>> ml = userMapper.findSchoolStaff(enterpriseid,realname,pageModel.getPage(), pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public int deleteSchooleStaff(long id, long teacherid) throws Exception {
		// TODO Auto-generated method stub
		int i = userMapper.deleteByPrimaryKey(id);
		int i1 = TeacherMapper.deleteByPrimaryKey(teacherid);
		if(i>0 && i1 >0)
		{
			return 1;
		}
		return 0;
	}

}
