package com.match.reviewresult.mapper;

import java.util.List;

import com.match.reviewresult.model.DepartmentScore;

public interface DepartmentScoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DepartmentScore record);

    int insertSelective(DepartmentScore record);

    DepartmentScore selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DepartmentScore record);

    int updateByPrimaryKey(DepartmentScore record);
    
    public  int  addBatchDepartmentScore(List<DepartmentScore> list)  throws  Exception;
}