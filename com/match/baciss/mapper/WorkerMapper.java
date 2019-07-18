package com.match.baciss.mapper;

import com.match.baciss.model.Worker;

public interface WorkerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Worker record);

    int insertSelective(Worker record);

    Worker selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Worker record);

    int updateByPrimaryKey(Worker record);
}