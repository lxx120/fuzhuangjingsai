package com.match.org.mapper;

import com.match.org.model.Production;

public interface ProductionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Production record);

    int insertSelective(Production record);

    Production selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Production record);

    int updateByPrimaryKeyWithBLOBs(Production record);

    int updateByPrimaryKey(Production record);
}