package com.match.baciss.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.match.baciss.model.JudgeDivision;

public interface JudgeDivisionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JudgeDivision record);

    int insertSelective(JudgeDivision record);

    JudgeDivision selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JudgeDivision record);

    int updateByPrimaryKey(JudgeDivision record);
    
    int  deleteJudgeDivisionByJudgeid(long judgeid)  throws Exception;
    
}