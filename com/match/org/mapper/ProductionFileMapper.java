package com.match.org.mapper;

import com.match.org.model.ProductionFile;

public interface ProductionFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductionFile record);

    int insertSelective(ProductionFile record);

    ProductionFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductionFile record);

    int updateByPrimaryKey(ProductionFile record);
}