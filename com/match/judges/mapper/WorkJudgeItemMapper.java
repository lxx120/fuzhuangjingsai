package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.judges.model.WorkJudge;
import com.match.judges.model.WorkJudgeItem;

@Mapper
public interface WorkJudgeItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkJudgeItem record);

    int insertSelective(WorkJudgeItem record);

    WorkJudgeItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkJudgeItem record);

    int updateByPrimaryKey(WorkJudgeItem record);
    
    /**
     * 
     * <p>功能描述：批量添加</p>
     * <p>方法名：addBatchWorkJudgeItem</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月26日 下午6:09:46</p>  
     * <p>创建者：lxx</p>
     */
    public int addBatchWorkJudgeItem(List<Map<String,Object>>  list)  throws Exception;
    
    @Select("select ruleID,score from workjudgeitem where workjudgeid=#{id}")
    public  List<Map<String,Object>>  findScoreByWorkJudgeId(long id)  throws  Exception;
}