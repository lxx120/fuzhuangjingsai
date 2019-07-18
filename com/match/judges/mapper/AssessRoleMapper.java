package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.judges.model.AssessRole;

@Mapper
public interface AssessRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssessRole record);

    int insertSelective(AssessRole record);

    AssessRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssessRole record);

    int updateByPrimaryKey(AssessRole record);
    
    int  deleteAssessRole(AssessRole assessRole)  throws  Exception;
    
//    @Select("select name,id from ")
//    List<Map<String,Object>>  findAssessRoleList(AssessRole assessRole)  throws  Exception;
}