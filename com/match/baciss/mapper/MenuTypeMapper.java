package com.match.baciss.mapper;

import com.match.baciss.model.MenuType;

public interface MenuTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuType record);

    int insertSelective(MenuType record);

    MenuType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuType record);

    int updateByPrimaryKey(MenuType record);
}