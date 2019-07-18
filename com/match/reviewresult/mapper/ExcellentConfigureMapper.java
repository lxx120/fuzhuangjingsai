package com.match.reviewresult.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.reviewresult.model.ExcellentConfigure;

@Mapper
public interface ExcellentConfigureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExcellentConfigure record);

    int insertSelective(ExcellentConfigure record);

    ExcellentConfigure selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExcellentConfigure record);

    int updateByPrimaryKey(ExcellentConfigure record);
    
    @Select("select excellentperson,excellentteacher,excellentcolldge,id from excellentconfigure where competationid=#{competitionid}")
    Map<String,Object>  findExcellentConfigure(long competitionid)  throws  Exception;
    
    @Select("select id from excellentconfigure where competationid=#{competitionid}")
    Map<String,Object>  findIsExist(long competitionid)  throws  Exception;
}