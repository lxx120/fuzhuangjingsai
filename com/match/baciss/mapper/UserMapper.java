package com.match.baciss.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.baciss.model.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    Map<String,Object>  findmember(Long id);
    
    List<Map<String,Object>>  findmemberpage(@Param("realName") String username,@Param("phone") String phone,@Param("page")int page, @Param("pagesize")int pagesize);
    
    public  int  count(User user)   throws  Exception;
    
    int  updateUser(User user)  throws  Exception;
    
    public  Map<String,Object>  findUserToPassword(@Param("username") String username)  throws  Exception;
    
    @Select("select id,password from user where id=#{id} ")
    public  Map<String,Object>  findUserToPasswordById(@Param("id") long id)  throws  Exception;

    
    @Select("select id,password from user where phone=#{username}")
    public  Map<String,Object>  findUserToPasswordByPhone(@Param("username") String username);
    
    /**
     * 
     * <p>功能描述：添加用户</p>
     * <p>方法名：addUser</p>
     * <p>@param user
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年3月22日 上午11:05:44</p>  
     * <p>创建者：lxx</p>
     */
    public  void  addUser(User user)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询用户名是否存在</p>
     * <p>方法名：findUserExist</p>
     * <p>@param username
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年3月22日 下午2:08:04</p>  
     * <p>创建者：lxx</p>
     */
    public  Map<String,Object>  findUserExist(String username)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：通过手机号查询是否存在</p>
     * <p>方法名：findUserExistByPhone</p>
     * <p>@param username
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年3月22日 下午5:13:52</p>  
     * <p>创建者：lxx</p>
     */
    public  Map<String,Object>  findUserExistByPhone(String phone)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：登录时查询的信息</p>
     * <p>方法名：findUserMessage</p>
     * <p>@param username
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月2日 上午10:08:29</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT a.`id`,a.`username`,a.`realName` as realname,b.`id` AS divisionid,c.`status`,c.`name` as comname,c.id as competitionid,a.enterpriseid FROM USER a LEFT JOIN division  b ON a.`phone`=b.`phone` LEFT JOIN Competition c ON b.`competitionid`=c.`id` WHERE username=#{username} order by b.ctime desc")
    public  List<Map<String,Object>> findUserMessage(String username)  throws  Exception;
    
    
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
	@Select("SELECT a.id,a.`realName`, a.`phone`, a.`qq`, a.`email`, a.`photo` FROM USER a where a.id=#{id}")
	Map<String,Object>  findTeacherInfo(long id)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询用户名是否存在</p>
	 * <p>方法名：findUserUsernameIsExists</p>
	 * <p>@param username
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月3日 下午4:12:01</p>  
	 * <p>创建者：lxx</p>
	 */
	Map<String,Object>  findUserUsernameIsExists(String username)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询学生详情</p>
	 * <p>方法名：findStudentInfomatation</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月3日 下午4:31:51</p>  
	 * <p>创建者：lxx</p>
	 */
	Map<String,Object>  findStudentInfomatation(long id) throws Exception;

	/**
	 * 
	 * <p>功能描述：查询企业详情</p>
	 * <p>方法名：findEnterpriseInformation</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月8日 上午10:50:15</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("SELECT a.photo,a.realName,b.province,b.city,b.name,b.`major`,b.address,b.people,b.ucNo,b.`content`,a.`qq`,a.`phone`,a.`email`,a.`zhifubao`,b.`answeringquestionpath`,b.`publicaddresspath`,a.`id`,a.`enterpriseid` FROM USER a LEFT JOIN enterprise b ON a.enterpriseid=b.`id` where a.id=#{id}")
	Map<String,Object>  findEnterpriseInformation(long id)  throws  Exception;
	
	@Select("select enterpriseid,type,realname,cardType,phone,cardID,weixin,email,zhifubao,qq from user where id=#{id}")
	Map<String,Object>  finduserInfo(long id)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：查询用户信息和学校所属的赛区</p>
	 * <p>方法名：findUserCollege</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月9日 下午5:49:04</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("SELECT a.isperfect,a.`type`,c.`divisionid` FROM USER a LEFT JOIN  Student b ON a.id=b.`userid` LEFT JOIN `divisionschool` c ON b.`college`=c.schoolid AND c.`competitionid`=#{competitionid} WHERE a.id=#{id}")
	Map<String,Object>  findUserCollege(@Param("id") long id,@Param("competitionid") long competitionid)  throws  Exception;
	
	/**
	 * 修改企业用
	 */
	@Select("select id from user where enterpriseid!=#{enid} and phone=#{phone}")
	Map<String,Object>  findUserPhone(@Param("phone") String phone,@Param("enid") long enid)  throws  Exception;
	
	@Delete("delete from user where phone=#{phone}")
	int  deleteUserByPhone(String phone)  throws  Exception;
	
	@Select("SELECT a.phone,a.leader,0 as isjudge,a.manager,a.isperfect,a.id,a.realName,a.enterpriseid,a.type,b.`id` AS judgeid  FROM USER a left join JudgeInformation b on a.id=b.userid WHERE a.id=#{id}")
	Map<String,Object>  findUserMessageWeb(long id)   throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-学校负责人查询学校人员列表</p>
	 * <p>方法名：findSchoolStaff</p>
	 * <p>@param realname
	 * <p>@param page
	 * <p>@param pagesize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年5月20日 下午5:21:13</p>  
	 * <p>创建者：lxx</p>
	 */
	List<Map<String,Object>>  findSchoolStaff(@Param("enterpriseid") long enterpriseid,@Param("realname") String realname,@Param("page") int page,@Param("pagesize") int pagesize)   throws  Exception;
	
	int countfindSchoolStaff(@Param("enterpriseid") long enterpriseid,@Param("realname") String realname)  throws  Exception;

}