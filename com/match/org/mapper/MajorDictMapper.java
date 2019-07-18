package com.match.org.mapper;

import java.util.List;
import java.util.Map;

import com.match.org.model.DepartMajor;
import com.match.org.model.MajorDict;

public interface MajorDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MajorDict record);

    int insertSelective(MajorDict record);

    MajorDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MajorDict record);

    int updateByPrimaryKey(MajorDict record);
    
}