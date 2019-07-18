package com.match.baciss.mapper;

import com.match.baciss.model.AuthenOperation;

public interface AuthenOperationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthenOperation record);

    int insertSelective(AuthenOperation record);

    AuthenOperation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthenOperation record);

    int updateByPrimaryKey(AuthenOperation record);
}