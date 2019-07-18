package com.match.baciss.service;

import java.util.Map;

import com.match.baciss.model.LoginMessage;
import com.match.baciss.model.Student;
import com.match.baciss.model.Teacher;
import com.match.baciss.model.User;
import com.match.baciss.model.Worker;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.org.model.Enterprise;


public interface UserServices {

	/**
	 * 
	 * <p>功能描述:删除用户</p>
	 * <p>方法名：deleteUser</p>
	 * <p>@param userid
	 * <p>@return</p>
	 * <p>返回类型：ObjectResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月18日 上午9:27:52</p>  
	 * <p>创建者：lxx</p>
	 */
	ObjectResult<Map<String,Object>>  deleteUser(Long userid);  
	
	/**
	 * 
	 * <p>功能描述：查询单个用户</p>
	 * <p>方法名：findmemeber</p>
	 * <p>@param userid
	 * <p>@return</p>
	 * <p>返回类型：ObjectResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月18日 上午9:28:10</p>  
	 * <p>创建者：lxx</p>
	 */
	ObjectResult<Map<String,Object>>  findmemeber(Long userid);
	
	/**
	 * 
	 * <p>功能描述：查询用户（分页+高级搜索）</p>
	 * <p>方法名：findmemberpage</p>
	 * <p>@param p
	 * <p>@return</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月19日 上午10:16:32</p>  
	 * <p>创建者：lxx</p>
	 */
	PageResult<Map<String,Object>>  findmemberpage(User user,PageModel p) throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改用户</p>
	 * <p>方法名：updateUser</p>
	 * <p>@param user
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：ObjectResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月22日 上午9:54:02</p>  
	 * <p>创建者：lxx</p>
	 */
	int  updateUser(User user,long roleid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：登录</p>
	 * <p>方法名：dologin</p>
	 * <p>@param username
	 * <p>@param password
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：ObjectResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月22日 上午10:34:19</p>  
	 * <p>创建者：lxx</p>
	 */
	ObjectResult<LoginMessage>  dologin(String username,String password)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：添加用户</p>
	 * <p>方法名：addUser</p>
	 * <p>@param user
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年3月22日 上午11:04:52</p>  
	 * <p>创建者：lxx</p>
	 */
	int  addUser(User user,long roleid) throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：通过查询手机号是否存在添加账号</p>
	 * <p>方法名：adduserByPhone</p>
	 * <p>@param user
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月22日 下午5:12:13</p>  
	 * <p>创建者：lxx</p>
	 */
	int  adduserByPhone(User user) throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：用户登录（web）</p>
	 * <p>方法名：dologinWeb</p>
	 * <p>@param username
	 * <p>@param password
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：ObjectResult<LoginMessage></p>
	 * <p>创建日期：2019年5月8日 上午9:34:54</p>  
	 * <p>创建者：lxx</p>
	 */
	ObjectResult<LoginMessage>  dologinWeb(String username,String password)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改用户密码</p>
	 * <p>方法名：updatePassword</p>
	 * <p>@param username
	 * <p>@param password
	 * <p>@param sepassword
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月3日 上午10:05:34</p>  
	 * <p>创建者：lxx</p>
	 */
	int  updatePassword(long  id,String oldpassword,String password,String sepassword)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：教师完善资质</p>
	 * <p>方法名：updateTeacherPerfect</p>
	 * <p>@param teacher
	 * <p>@param user
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月3日 上午11:35:23</p>  
	 * <p>创建者：lxx</p>
	 */
	int  updateTeacherPerfect(Teacher teacher,User user)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：教师详情</p>
	 * <p>方法名：findTeacherInformatation</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月3日 下午2:09:36</p>  
	 * <p>创建者：lxx</p>
	 */
	Map<String,Object> findTeacherInformatation(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询教师资料</p>
	 * <p>方法名：findTeacherInfo</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月3日 下午2:10:14</p>  
	 * <p>创建者：lxx</p>
	 */
	Map<String,Object>  findTeacherInfo(long id)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：修改用户头像</p>
	 * <p>方法名：updateUserPhoto</p>
	 * <p>@param user
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月3日 下午2:55:43</p>  
	 * <p>创建者：lxx</p>
	 */
	int updateUserPhoto(User user)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询学生详情</p>
	 * <p>方法名：findStudentInfomatation</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月3日 下午4:33:20</p>  
	 * <p>创建者：lxx</p>
	 */
	Map<String,Object> findStudentInfomatation(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：学生完善资质</p>
	 * <p>方法名：updateStudentPerfect</p>
	 * <p>@param teacher
	 * <p>@param user
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月3日 下午4:35:07</p>  
	 * <p>创建者：lxx</p>
	 */
	int  updateStudentPerfect(Student student,User user)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询企业详情</p>
	 * <p>方法名：findEnterpriseInformation</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月8日 上午10:41:46</p>  
	 * <p>创建者：lxx</p>
	 */
	Map<String,Object>  findEnterpriseInformation(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：完善企业资质</p>
	 * <p>方法名：updateEnterprisePerfect</p>
	 * <p>@param exEnterprise
	 * <p>@param user
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月8日 上午10:45:06</p>  
	 * <p>创建者：lxx</p>
	 */
	int  updateEnterprisePerfect(User user)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：注册学生用户</p>
	 * <p>方法名：AddCollegeStaff</p>
	 * <p>@param user
	 * <p>@param teacher
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月12日 下午5:59:38</p>  
	 * <p>创建者：lxx</p>
	 */
	int  AddStudentStaff(User user)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：学校负责人-添加教师</p>
	 * <p>方法名：addSchoolStaff</p>
	 * <p>@param user
	 * <p>@param teacher
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月20日 下午4:03:04</p>  
	 * <p>创建者：lxx</p>
	 */
	int addSchoolStaff(User user,Teacher teacher)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：企业负责人-添加企业员工</p>
	 * <p>方法名：AddCompanyStaff</p>
	 * <p>@param user
	 * <p>@param worker
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月16日 下午6:11:44</p>  
	 * <p>创建者：lxx</p>
	 */
	int  AddCompanyStaff(User user,Worker worker)  throws  Exception;
	
	/**
	 * 
	 * <p>方法名：finduserInfo</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月13日 下午5:40:05</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  finduserInfo(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-学校负责人查询学校人员列表</p>
	 * <p>方法名：findSchoolStaff</p>
	 * <p>@param name
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年5月20日 下午5:19:07</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>> findSchoolStaff(long enterpriseid,String realname,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-学校负责人删除员工</p>
	 * <p>方法名：deleteSchooleStaff</p>
	 * <p>@param id
	 * <p>@param teacherid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月21日 上午9:45:20</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  deleteSchooleStaff(long id,long teacherid)  throws  Exception;
}
