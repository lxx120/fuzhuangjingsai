package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.ThemeWorkFile;

public interface ThemeWorkFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThemeWorkFile record);

    int insertSelective(ThemeWorkFile record);

    ThemeWorkFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThemeWorkFile record);

    int updateByPrimaryKey(ThemeWorkFile record);
    
    /**
     * 
     * <p>功能描述：批量添加文件</p>
     * <p>方法名：BatchAddThemeWorkFile</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月11日 下午3:01:55</p>  
     * <p>创建者：lxx</p>
     */
    int BatchAddThemeWorkFile(List<Map<String,Object>> list) throws Exception;
    
    /**
     * 
     * <p>功能描述：批量删除</p>
     * <p>方法名：deleteThemeWorkByThemeworkid</p>
     * <p>@param themeworkid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月11日 下午4:22:31</p>  
     * <p>创建者：lxx</p>
     */
    @Delete("delete from themeworkfile where themeworkid=#{themeworkid}")
    int  deleteThemeWorkByThemeworkid(long themeworkid)  throws Exception;
    
    /**
     * 
     * <p>功能描述：查询提交的文件列表</p>
     * <p>方法名：findMyThemeWorkFile</p>
     * <p>@param themeworkid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月11日 下午5:19:01</p>  
     * <p>创建者：lxx</p>
     */
    @Select("select code,name,size,path from themeworkfile where themeworkid=#{themeworkid}")
    List<Map<String,Object>>  findMyThemeWorkFile(long themeworkid)  throws  Exception;
}