package com.match.news.mapper;

import java.util.List;
import java.util.Map;

import com.match.news.model.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(Category record) throws Exception;

    int insertSelective(Category record) throws Exception;

    Category selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(Category record) throws Exception;

    int updateByPrimaryKey(Category record) throws Exception;
    
    Map<String,Object>  findCategoryById(long id)  throws Exception;
    
    List<Map<String,Object>>  findCategoryList()  throws  Exception;
    
    Map<String,Object>  findMaxOrder()  throws  Exception;
}