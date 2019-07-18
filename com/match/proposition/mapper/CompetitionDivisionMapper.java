package com.match.proposition.mapper;

import com.match.proposition.model.CompetitionDivision;

public interface CompetitionDivisionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompetitionDivision record);

    int insertSelective(CompetitionDivision record);

    CompetitionDivision selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompetitionDivision record);

    int updateByPrimaryKey(CompetitionDivision record);
}