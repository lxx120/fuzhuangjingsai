package com.match.news.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.news.model.ContentFile;

@Mapper
public interface ContentFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContentFile record);

    int insertSelective(ContentFile record);

    ContentFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContentFile record);

    int updateByPrimaryKey(ContentFile record);
    
    @Delete("delete from contentfile where contentID=#{contentID}")
    int  deleteByContentId(long contentID)  throws Exception;
    
    List<Map<String,Object>>  findContentFileList(long id)  throws Exception;
    
    /**
     * 
     * <p>功能描述：分页时查询每个新闻的图片</p>
     * <p>方法名：findContentFile</p>
     * <p>@param s
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年5月7日 下午3:51:15</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findContentFile(List<String> s)  throws  Exception;
}