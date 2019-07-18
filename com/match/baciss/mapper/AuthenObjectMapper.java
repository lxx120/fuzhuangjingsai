package com.match.baciss.mapper;

import com.match.baciss.model.AuthenObject;

public interface AuthenObjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthenObject record);

    int insertSelective(AuthenObject record);

    AuthenObject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthenObject record);

    int updateByPrimaryKey(AuthenObject record);
}