package com.match.reviewresult.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.match.reviewresult.model.ExcellentThemeWork;

@Mapper
public interface ExcellentThemeWorkMapper {

	/**
	 * 
	 * <p>功能描述：批量添加</p>
	 * <p>方法名：addBatchExcellentThemeWork</p>
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月3日 上午10:13:58</p>  
	 * <p>创建者：lxx</p>
	 */
	public int addBatchExcellentThemeWork(List<ExcellentThemeWork>  list)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除</p>
	 * <p>方法名：deleteExcellentThemeWork</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月3日 上午11:25:17</p>  
	 * <p>创建者：lxx</p>
	 */
	public int deleteExcellentThemeWork(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询优秀作品</p>
	 * <p>方法名：findExcellentThemeWork</p>
	 * <p>@param pagesize
	 * <p>@param competitionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年7月3日 上午11:25:25</p>  
	 * <p>创建者：lxx</p>
	 */
	public List<Map<String,Object>> findExcellentThemeWork(@Param("pagesize") int pagesize,@Param("competitionid") long competitionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询未被选择的作品</p>
	 * <p>方法名：findOtherThemeWork</p>
	 * <p>@param stwcode
	 * <p>@param code
	 * <p>@param phone
	 * <p>@param page
	 * <p>@param pagesize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年7月3日 下午4:01:50</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>> findOtherThemeWork(@Param("competitionid") long competitionid,@Param("stwcode") String stwcode,@Param("code") String code,@Param("phone") String phone,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
	
	public  int countfindOtherThemeWork(@Param("competitionid") long competitionid,@Param("stwcode") String stwcode,@Param("code") String code,@Param("phone") String phone)  throws  Exception;

}
