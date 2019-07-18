package com.match.org.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.org.model.propositionstrategy;

@Mapper
public interface propositionstrategyMapper {

	public int addPropositionstrategy(propositionstrategy propositionstrategy) throws Exception;

	public int updatePropositionstrategy(propositionstrategy propositionstrategy) throws Exception;
	
	@Select("select logoelement,relevantinfo,adverteform,advertepurposes,targetgroups,name,productcontent,productinfo,id from PropositionStrategy where id=#{id}")
	public  Map<String,Object>  findPropositionstrategy(@Param("id")long id)  throws  Exception;
	
}