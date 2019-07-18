package com.match.baciss.mapper;

import com.match.baciss.model.ActionLog;

public interface ActionLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActionLog record);

    int insertSelective(ActionLog record);

    ActionLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActionLog record);

    int updateByPrimaryKey(ActionLog record);
}