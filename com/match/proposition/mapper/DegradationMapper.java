package com.match.proposition.mapper;

import com.match.proposition.model.Degradation;

public interface DegradationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Degradation record);

    int insertSelective(Degradation record);

    Degradation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Degradation record);

    int updateByPrimaryKeyWithBLOBs(Degradation record);

    int updateByPrimaryKey(Degradation record);
}