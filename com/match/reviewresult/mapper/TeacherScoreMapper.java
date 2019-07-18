package com.match.reviewresult.mapper;

import java.util.List;

import com.match.reviewresult.model.TeacherScore;

public interface TeacherScoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeacherScore record);

    int insertSelective(TeacherScore record);

    TeacherScore selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeacherScore record);

    int updateByPrimaryKey(TeacherScore record);
    
    public  int  addBatchTeacherScore(List<TeacherScore> list)  throws  Exception;
}