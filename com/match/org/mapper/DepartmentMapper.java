package com.match.org.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.org.model.Department;

@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    /**
     * 
     * <p>功能描述：根据学校id查询院系</p>
     * <p>方法名：findDepartmentList</p>
     * <p>@param department
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月3日 下午5:30:59</p>  
     * <p>创建者：lxx</p>
     */
    public  List<Map<String,Object>>  findDepartmentList(Department department)  throws  Exception;
    
    @Select("select id,dename,indx from department where id=#{id}")
    public  Map<String,Object>  findDepartmentById(long id)  throws  Exception;
    
    @Select("select id from department where dename=#{department} and collegeid=#{id}")
    public  Map<String,Object>  findDepartmentExist(@Param("department") String department,@Param("id") long id)  throws  Exception;
}