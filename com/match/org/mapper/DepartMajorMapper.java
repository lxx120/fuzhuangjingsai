package com.match.org.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.org.model.DepartMajor;

@Mapper
public interface DepartMajorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DepartMajor record);

    int insertSelective(DepartMajor record);

    DepartMajor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DepartMajor record);

    int updateByPrimaryKey(DepartMajor record);
    
    public  List<Map<String,Object>>  findDepartMajorList(DepartMajor departMajor)  throws  Exception;
    
    @Select("select  major,code,id from departmajor where id=#{id}")
    public  Map<String,Object>  findDepartMajorByid(long id)  throws   Exception;
}