package com.match.reviewresult.mapper;

import java.util.List;

import com.match.reviewresult.model.PersonalScore;

public interface PersonalScoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PersonalScore record);

    int insertSelective(PersonalScore record);

    PersonalScore selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PersonalScore record);

    int updateByPrimaryKey(PersonalScore record);
    
    public  int  addBatchPersonalScore(List<PersonalScore> list)  throws  Exception;
}