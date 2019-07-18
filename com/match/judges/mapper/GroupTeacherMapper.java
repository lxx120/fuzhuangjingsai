package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.match.judges.model.GroupTeacher;

@Mapper
public interface GroupTeacherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupTeacher record);

    int insertSelective(GroupTeacher record);

    GroupTeacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupTeacher record);

    int updateByPrimaryKey(GroupTeacher record);
    
    /**
     * 
     * <p>功能描述：批量添加</p>
     * <p>方法名：insertList</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年3月30日 上午11:12:23</p>  
     * <p>创建者：lxx</p>
     */
    int  insertList(List<Map<String,Object>>  list)  throws  Exception;
    
    @Select("SELECT a.weight,a.`teacherid`,f.`divisionid`,f.`competitionid`,f.`id` as arid,a.id,b.wantCheckTheme,c.`realName`,c.`phone`,a.groupid FROM `groupteacher` a LEFT JOIN `judgeinformation` b ON a.`teacherid`=b.`id` LEFT JOIN `user` c ON b.`userid`=c.`id` LEFT JOIN AssessGroup g ON a.`groupid`=g.`id` LEFT JOIN assessrole d ON g.`arrid` = d.id LEFT JOIN assessrounds f ON d.`arid` = f.`id`  where a.groupid=#{groupid} order by a.ctime")
    List<Map<String,Object>>  findGroupTeacherList(long groupid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：批量修改</p>
     * <p>方法名：updateBatchGroupTeacher</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年3月30日 下午2:20:01</p>  
     * <p>创建者：lxx</p>
     */
    int  updateBatchGroupTeacher(List<GroupTeacher>  list)  throws  Exception;
    
    @Update("update groupteacher set weight=0 where groupid=#{groupid}")
    int  updateGroupTeacherWeight(long groupid)  throws  Exception;
}