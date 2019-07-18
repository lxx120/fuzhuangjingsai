package com.match.proposition.mapper;

import com.match.proposition.model.CompetitionFiles;

public interface CompetitionFilesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompetitionFiles record);

    int insertSelective(CompetitionFiles record);

    CompetitionFiles selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompetitionFiles record);

    int updateByPrimaryKey(CompetitionFiles record);
}