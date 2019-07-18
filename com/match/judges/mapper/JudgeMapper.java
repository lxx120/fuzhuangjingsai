package com.match.judges.mapper;

import com.match.judges.model.Judge;

public interface JudgeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Judge record);

    int insertSelective(Judge record);

    Judge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Judge record);

    int updateByPrimaryKey(Judge record);
}