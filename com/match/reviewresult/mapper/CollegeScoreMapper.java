package com.match.reviewresult.mapper;

import java.util.List;

import com.match.reviewresult.model.CollegeScore;

public interface CollegeScoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CollegeScore record);

    int insertSelective(CollegeScore record);

    CollegeScore selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollegeScore record);

    int updateByPrimaryKey(CollegeScore record);
    
    public  int  addBatchCollegeScore(List<CollegeScore> list)  throws  Exception;
}