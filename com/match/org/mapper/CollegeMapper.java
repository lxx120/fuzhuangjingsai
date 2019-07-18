package com.match.org.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.org.model.College;
import com.match.proposition.model.Theme;

@Mapper
public interface CollegeMapper {
    int deleteByPrimaryKey(Long id) throws  Exception;

    int insert(College record) throws  Exception;

    int insertSelective(College record) throws  Exception;

    College selectByPrimaryKey(Long id) throws  Exception;

    int updateByPrimaryKeySelective(College record) throws  Exception;

    int updateByPrimaryKey(College record) throws  Exception;
    
    List<Map<String,Object>>  findCollegePage(@Param("name") String name,@Param("province") String province,@Param("city") String city,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    List<Map<String,Object>>   findCollegeById(long id) throws Exception;
    
    int  count(College college)  throws  Exception;
    
    List<Map<String,Object>> findCollegeList(@Param("name") String name)  throws Exception;

    
	List<Map<String, Object>> findGoodCollegeListPage(@Param("page")int page,@Param("pagesize") int pagesize);

	@Select("select id,name from college where id=#{id}")
	Map<String,Object>  findCollegeInfo(long id)  throws  Exception;
	
	public  int  counts(College college)   throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-学校参赛情况</p>
	 * <p>方法名：findCollegeJoinCompetition</p>
	 * <p>@param competitionid
	 * <p>@param collegeid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年5月21日 下午5:37:56</p>  
	 * <p>创建者：lxx</p>
	 */
	List<Map<String,Object>>  findCollegeJoinCompetition(@Param("competitionid") long competitionid,@Param("collegeid") long collegeid,@Param("page") int page,@Param("pagesize") int pagesize) throws  Exception;
	
	int  countfindCollegeJoinCompetition(@Param("competitionid") long competitionid,@Param("collegeid") long collegeid)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：前端-院校领导查询指导教师</p>
	 * <p>方法名：findCollegeTeacher</p>
	 * <p>@param competitionid
	 * <p>@param enterpriseid
	 * <p>@param page
	 * <p>@param pagesize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年6月15日 下午2:24:42</p>  
	 * <p>创建者：lxx</p>
	 */
	List<Map<String,Object>>  findCollegeTeacher(@Param("competitionid") long competitionid,@Param("enterpriseid") long enterpriseid,@Param("page") int page,@Param("pagesize")  int pagesize)  throws  Exception;
	
	int  countfindCollegeTeacher(@Param("competitionid") long competitionid,@Param("enterpriseid") long enterpriseid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-院校领导查询参赛学生</p>
	 * <p>方法名：findCollegeStudent</p>
	 * <p>@param competitionid
	 * <p>@param enterpriseid
	 * <p>@param page
	 * <p>@param pagesize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年6月15日 下午2:39:11</p>  
	 * <p>创建者：lxx</p>
	 */
	List<Map<String,Object>>  findCollegeStudent(@Param("jename") String jename,@Param("major") String major, @Param("competitionid") long competitionid,@Param("enterpriseid") long enterpriseid,@Param("page") int page,@Param("pagesize")  int pagesize)  throws  Exception;
	
	int  countfindCollegeStudent(@Param("jename") String jename,@Param("major") String major,@Param("competitionid") long competitionid,@Param("enterpriseid") long enterpriseid)  throws  Exception;
	
	
	/**
	 * 
	 * <p>功能描述：后台-赛区负责人查询赛区所参加的学校</p>
	 * <p>方法名：fingCollegeByDivision</p>
	 * <p>@param name
	 * <p>@param competitionid
	 * <p>@param divisionid
	 * <p>@param page
	 * <p>@param pagesize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年6月15日 下午3:00:54</p>  
	 * <p>创建者：lxx</p>
	 */
	List<Map<String,Object>>  fingCollegeByDivision(@Param("name") String name,@Param("competitionid") long competitionid,@Param("divisionid") long divisionid,@Param("page") int page,@Param("pagesize")  int pagesize)  throws Exception;
	
	int countfingCollegeByDivision(@Param("name") String name,@Param("competitionid") long competitionid,@Param("divisionid") long divisionid)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：导数据用</p>
	 * <p>方法名：findCollegeExist</p>
	 * <p>@param collegecode
	 * <p>@param college
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年6月19日 下午2:50:57</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("select id from college where name=#{college} and code=#{collegecode}")
	Map<String,Object>  findCollegeExist(@Param("collegecode")  String collegecode,@Param("college")  String college)  throws  Exception;
}