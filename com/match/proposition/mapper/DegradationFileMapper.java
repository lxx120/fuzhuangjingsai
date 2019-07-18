package com.match.proposition.mapper;

import com.match.proposition.model.DegradationFile;

public interface DegradationFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DegradationFile record);

    int insertSelective(DegradationFile record);

    DegradationFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DegradationFile record);

    int updateByPrimaryKey(DegradationFile record);
}