package com.match.judges.mapper;

import com.match.judges.model.AssessHierarchy;

public interface AssessHierarchyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssessHierarchy record);

    int insertSelective(AssessHierarchy record);

    AssessHierarchy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssessHierarchy record);

    int updateByPrimaryKey(AssessHierarchy record);
}