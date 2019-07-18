package com.match.news.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.news.model.Content;

@Mapper
public interface ContentMapper {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(Content record) throws Exception;

    int insertSelective(Content record) throws Exception;

    Content selectByPrimaryKey(Long id)throws Exception;

    int updateByPrimaryKeySelective(Content record) throws  Exception;

    int updateByPrimaryKeyWithBLOBs(Content record) throws Exception;

    int updateByPrimaryKey(Content record) throws Exception;
    
    List<Map<String,Object>>  findContentPage(@Param("title") String title,@Param("page") int page,@Param("pagesize")  int pagesize);
    
    Map<String,Object>  findContentByID(long id)  throws Exception;
    
    int count(Content content)  throws Exception;
    
    Map<String,Object>  findMaxViewsById(long id)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：web-查询单个新闻</p>
     * <p>方法名：findfindContentByIDWeb</p>
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月2日 下午5:48:43</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT c.`caname`,b.`path`,a.`abstraction`,a.`content`,a.`creator`,a.`views`,a.`title`,DATE_FORMAT(a.`ctime`,'%Y-%m-%d') AS ctime,a.`id` FROM Content a LEFT JOIN ContentFile b ON a.id=b.`contentID` LEFT JOIN Category c ON  a.`categoryid`=c.`id` where a.id=#{id}")
    Map<String,Object>  findfindContentByIDWeb(long id)  throws  Exception;
    
    
    List<Map<String,Object>>  findContentPageWeb(@Param("page") int page,@Param("pagesize")  int pagesize);
    
    
    int countWeb()  throws Exception;
}