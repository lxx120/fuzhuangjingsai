package com.match.baciss.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.baciss.model.Student;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    @Select("select id from student where userid=#{userid}")
    Map<String,Object>  findStudentinformation(long userid) throws Exception;
    
    int  updateStudentByuserid(Student student) throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询学生用户，查询学校，院系，专业</p>
     * <p>方法名：findStudent</p>
     * <p>@param userid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年6月3日 下午2:28:16</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT b.`id` AS collegeid,b.`name` AS collegename,c.`id` AS departmentid,c.`dename`,d.`id` AS majorid,d.`major` FROM student a LEFT JOIN college b ON a.`college`=b.`id` LEFT JOIN department c ON a.`department`=c.`id` LEFT JOIN departmajor d ON a.`major`=d.`id` where a.userid= #{userid}")
    Map<String,Object> findStudent(long userid)  throws  Exception;
}