package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.judges.model.JudgeRule;

@Mapper
public interface JudgeRuleMapper {
    int deleteByPrimaryKey(Long id)  throws Exception;

    int insert(JudgeRule record);

    int insertSelective(JudgeRule record);

    JudgeRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JudgeRule record);

    int updateByPrimaryKeyWithBLOBs(JudgeRule record)  throws Exception;

    int updateByPrimaryKey(JudgeRule record);
    
    int insetjudgeRuleList(List list)  throws Exception;
    
    @Select("select id,arid,jrname,score,weight,content from JudgeRule where arid=#{arid}")
    List<Map<String,Object>>  findjudgeRuleList(long arid)  throws Exception;
    
    @Select("select id,arid,jrname,score,weight,content from JudgeRule where id=#{id}")
    Map<String,Object>  findjudgeRuleInfo(long id)  throws Exception;
}