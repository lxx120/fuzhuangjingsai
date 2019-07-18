package com.match.proposition.mapper;

import java.util.List;

import com.match.proposition.model.ThemeType;

public interface ThemeTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThemeType record);

    int insertSelective(ThemeType record);

    ThemeType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThemeType record);

    int updateByPrimaryKey(ThemeType record);
    
    /**
     * 
     * <p>功能描述：批量添加</p>
     * <p>方法名：addBatchThemeType</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月8日 下午5:29:15</p>  
     * <p>创建者：lxx</p>
     */
    int addBatchThemeType(List<ThemeType> list)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：根据命题删除</p>
     * <p>方法名：deleteThemeTypeByThemeid</p>
     * <p>@param themeid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月8日 下午6:14:16</p>  
     * <p>创建者：lxx</p>
     */
    int  deleteThemeTypeByThemeid(long themeid)  throws  Exception;
}