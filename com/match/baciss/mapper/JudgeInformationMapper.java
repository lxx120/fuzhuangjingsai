package com.match.baciss.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.baciss.model.JudgeInformation;

@Mapper
public interface JudgeInformationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JudgeInformation record);

    int insertSelective(JudgeInformation record);

    JudgeInformation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JudgeInformation record);

    int updateByPrimaryKey(JudgeInformation record);
    
    /**
     * 
     * <p>功能描述：查询评委是否存在</p>
     * <p>方法名：findJudgeIsExist</p>
     * <p>@param judgephone
     * <p>@return</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年3月26日 上午10:27:26</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>   findJudgeIsExist(String judgephone) throws Exception;
    
    List<Map<String,Object>>  findJUdgeInformationPage(@Param("divisionid") long divisionid,@Param("judgename") String judgename,@Param("page") int page,@Param("pagesize") int pagesize)  throws Exception;
    
    Map<String,Object>  findJudgeInformationById(long id)  throws Exception;
    
    @Select("select levelAccess,id from judgeinformation where userid=#{userid}")
    Map<String,Object>  findJudgeInformation(long userid)  throws Exception;

    
    int count (JudgeInformation judgeInformation)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：分组时查询总赛区没有被绑定的评委</p>
     * <p>方法名：findGroupToJUdgeInformationPage</p>
     * <p>@param diid
     * <p>@param judgename
     * <p>@param page
     * <p>@param pagesize
     * <p>@param groupid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 下午5:13:50</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findGroupToJUdgeInformationPage(@Param("diid") long diid,@Param("judgename") String judgename,@Param("page") int page,@Param("pagesize") int pagesize,@Param("groupid") long groupid)  throws Exception;
    
    int Groupcount (@Param("divisionid") long divisionid,@Param("judgename") String judgename,@Param("groupid") long groupid,@Param("diid") long diid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：分组时查询赛区没有被绑定的评委</p>
     * <p>方法名：findDivisionGroupToJUdgeInformationPage</p>
     * <p>@param diid
     * <p>@param judgename
     * <p>@param page
     * <p>@param pagesize
     * <p>@param groupid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 下午5:16:09</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findDivisionGroupToJUdgeInformationPage(@Param("diid") long diid,@Param("judgename") String judgename,@Param("page") int page,@Param("pagesize") int pagesize,@Param("groupid") long groupid)  throws Exception;
    
    int DivisionGroupcount (@Param("divisionid") long divisionid,@Param("judgename") String judgename,@Param("groupid") long groupid,@Param("diid") long diid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询全局选中的学校评委</p>
     * <p>方法名：findManagerChooseJudge</p>
     * <p>@param realname
     * <p>@param name
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 上午9:47:39</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findManagerChooseSchoolJudge(@Param("realname") String realname,@Param("name") String name,@Param("page") int page,@Param("pagesize")  int pagesize)  throws  Exception;
    
    int  countManagerChooseSchoolJudge(@Param("realname") String realname,@Param("name") String name) throws Exception;
    
    /**
     * 
     * <p>功能描述：查询全局选中的企业评委</p>
     * <p>方法名：findManagerChooseCompanyJudge</p>
     * <p>@param realname
     * <p>@param name
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 上午9:58:15</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findManagerChooseCompanyJudge(@Param("realname") String realname,@Param("name") String name,@Param("page") int page,@Param("pagesize")  int pagesize)  throws  Exception;
    
    int  countManagerChooseCompanyJudge(@Param("realname") String realname,@Param("name") String name) throws Exception;

    /**
     * 
     * <p>功能描述：查询赛区选中的学校评委</p>
     * <p>方法名：findDivisionChooseSchoolJudge</p>
     * <p>@param divisionid
     * <p>@param realname
     * <p>@param name
     * <p>@param page
     * <p>@param pagesize
     * <p>@return</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 上午10:14:51</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findDivisionChooseSchoolJudge(@Param("divisionid") long divisionid,@Param("realname") String realname,@Param("name") String name,@Param("page") int page,@Param("pagesize")  int pagesize);
    
    int countDivisionChooseSchoolJudge(@Param("divisionid") long divisionid,@Param("realname") String realname,@Param("name") String name)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询赛区选中的企业评委</p>
     * <p>方法名：findDivisionChooseCompanyJudge</p>
     * <p>@param divisionid
     * <p>@param realname
     * <p>@param name
     * <p>@param page
     * <p>@param pagesize
     * <p>@return</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 上午10:16:35</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findDivisionChooseCompanyJudge(@Param("divisionid") long divisionid,@Param("realname") String realname,@Param("name") String name,@Param("page") int page,@Param("pagesize")  int pagesize);
    
    int countDivisionChooseCompanyJudge(@Param("divisionid") long divisionid,@Param("realname") String realname,@Param("name") String name)  throws  Exception;
    
    
    /**
     * 
     * <p>功能描述：查询全局未选中的学校评委</p>
     * <p>方法名：findManagerChooseJudge</p>
     * <p>@param realname
     * <p>@param name
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 上午9:47:39</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findManagerNoChooseSchoolJudge(@Param("realname") String realname,@Param("name") String name,@Param("page") int page,@Param("pagesize")  int pagesize)  throws  Exception;
    
    int  countManagerNoChooseSchoolJudge(@Param("realname") String realname,@Param("name") String name) throws Exception;
    
    /**
     * 
     * <p>功能描述：查询全局未选中的企业评委</p>
     * <p>方法名：findManagerChooseCompanyJudge</p>
     * <p>@param realname
     * <p>@param name
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 上午9:58:15</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findManagerNoChooseCompanyJudge(@Param("realname") String realname,@Param("name") String name,@Param("page") int page,@Param("pagesize")  int pagesize)  throws  Exception;
    
    int  countManagerNoChooseCompanyJudge(@Param("realname") String realname,@Param("name") String name) throws Exception;

    /**
     * 
     * <p>功能描述：查询赛区未选中的学校评委</p>
     * <p>方法名：findDivisionChooseSchoolJudge</p>
     * <p>@param divisionid
     * <p>@param realname
     * <p>@param name
     * <p>@param page
     * <p>@param pagesize
     * <p>@return</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 上午10:14:51</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findDivisionNoChooseSchoolJudge(@Param("pinjie") String pinjie,@Param("divisionid") long divisionid,@Param("realname") String realname,@Param("name") String name,@Param("page") int page,@Param("pagesize")  int pagesize);
    
    int countDivisionNoChooseSchoolJudge(@Param("pinjie") String pinjie,@Param("divisionid") long divisionid,@Param("realname") String realname,@Param("name") String name)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询赛区未选中的企业评委</p>
     * <p>方法名：findDivisionChooseCompanyJudge</p>
     * <p>@param divisionid
     * <p>@param realname
     * <p>@param name
     * <p>@param page
     * <p>@param pagesize
     * <p>@return</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 上午10:16:35</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findDivisionNoChooseCompanyJudge(@Param("pinjie") String pinjie,@Param("divisionid") long divisionid,@Param("realname") String realname,@Param("name") String name,@Param("page") int page,@Param("pagesize")  int pagesize);
    
    int countDivisionNoChooseCompanyJudge(@Param("pinjie") String pinjie,@Param("divisionid") long divisionid,@Param("realname") String realname,@Param("name") String name)  throws  Exception;
    
	/**
	 * 
	 * <p>功能描述：前端页面-通过用户id查询评委详情</p>
	 * <p>方法名：findJudgeInformationByUserid</p>
	 * <p>@param userid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月10日 下午5:06:22</p>  
	 * <p>创建者：lxx</p>
	 */
    @Select("select a.id,bankname,banknumber,bankaddress,resumeurl,skilledCheck,b.name,wantCheckTheme from judgeinformation a left join PropositionType b on a.wantCheckTheme=b.code where userid=#{userid}")
    Map<String,Object>  findJudgeInformationByUserid(long userid) throws  Exception;
}