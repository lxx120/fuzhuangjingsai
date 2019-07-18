package com.match.proposition.mapper;

import com.match.proposition.model.Publicity;

public interface PublicityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Publicity record);

    int insertSelective(Publicity record);

    Publicity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Publicity record);

    int updateByPrimaryKeyWithBLOBs(Publicity record);

    int updateByPrimaryKey(Publicity record);
}