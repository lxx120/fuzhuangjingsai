package com.match.proposition.mapper;

import com.match.proposition.model.ThemeMaterial;

public interface ThemeMaterialMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThemeMaterial record);

    int insertSelective(ThemeMaterial record);

    ThemeMaterial selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThemeMaterial record);

    int updateByPrimaryKey(ThemeMaterial record);
}