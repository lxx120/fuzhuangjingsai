package com.match.baciss.mapper;

import com.match.baciss.model.AuthenResource;

public interface AuthenResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthenResource record);

    int insertSelective(AuthenResource record);

    AuthenResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthenResource record);

    int updateByPrimaryKey(AuthenResource record);
}