package com.match.baciss.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.baciss.model.Teacher;

@Mapper
public interface TeacherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    @Select("select id from teacher where userid=#{userid}")
    Map<String,Object>  findTeacherinformation(long userid) throws Exception;
    
    /**
     * 
     * <p>功能描述：查询所有老师</p>
     * <p>方法名：findAllTeacher</p>
     * <p>@param name
     * <p>@param phone
     * <p>@param collegeid
     * <p>@param departmentid
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年6月28日 上午10:33:08</p>  
     * <p>创建者：lxx</p>
     */
    public List<Map<String,Object>>  findAllTeacher(@Param("name") String name,@Param("phone") String phone,@Param("collegeid") long collegeid,@Param("departmentid") long departmentid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    public  int  countfindAllTeacher(@Param("name") String name,@Param("phone") String phone,@Param("collegeid") long collegeid,@Param("departmentid") long departmentid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：前端，老师积分奖项</p>
     * <p>方法名：findTeacherScoreList</p>
     * <p>@param competitionid
     * <p>@param phone
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年7月4日 下午6:04:02</p>  
     * <p>创建者：lxx</p>
     */
    public List<Map<String,Object>>  findTeacherScoreList(@Param("prizeid") long prizeid,@Param("competitionid") long competitionid,@Param("phone") String phone,@Param("page") int page,@Param("pagesize") int pagesize) throws Exception;
    
    public  int countfindTeacherScoreList(@Param("prizeid") long prizeid,@Param("competitionid") long competitionid,@Param("phone") String phone)  throws  Exception;
	
}