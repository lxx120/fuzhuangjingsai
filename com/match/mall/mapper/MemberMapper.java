package com.match.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.match.mall.persist.Member;

public interface MemberMapper {

	public Member getByName(@Param("username")String username);

	public List<Map<String, Object>> getmemberList(@Param("username")String username, @Param("phone")String phone, @Param("mail")String mail, @Param("enterprise")String enterprise, @Param("page")int page, @Param("pagesize")int pagesize);

	public int count(Member member);

	public Member getById(@Param("id")long id);

	public void delete(long id);

}
