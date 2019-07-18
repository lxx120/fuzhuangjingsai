package com.match.proposition.mapper;

import com.match.proposition.model.View;

public interface ViewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(View record);

    int insertSelective(View record);

    View selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(View record);

    int updateByPrimaryKey(View record);
}