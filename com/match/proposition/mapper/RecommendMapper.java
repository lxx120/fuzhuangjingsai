package com.match.proposition.mapper;

import com.match.proposition.model.Recommend;

public interface RecommendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Recommend record);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Recommend record);

    int updateByPrimaryKey(Recommend record);
}