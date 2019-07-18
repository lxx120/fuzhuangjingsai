package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.match.judges.model.WorksAccessResult;

public interface WorksAccessResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorksAccessResult record);

    int insertSelective(WorksAccessResult record);

    WorksAccessResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorksAccessResult record);

    int updateByPrimaryKey(WorksAccessResult record);
    
    /**
     * 
     * <p>功能描述：批量添加</p>
     * <p>方法名：batchAddWorksAccessResult</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月28日 下午3:44:36</p>  
     * <p>创建者：lxx</p>
     */
    int  batchAddWorksAccessResult(List<WorksAccessResult> list)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：通过轮次ID看排名</p>
     * <p>方法名：findWorksAccessResult</p>
     * <p>@param arid
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月28日 下午4:47:47</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findWorksAccessResult(@Param("code1") String code1,@Param("arid") long arid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    int  countfindWorksAccessResult(@Param("code1") String code1,@Param("arid") long arid)  throws Exception;
}