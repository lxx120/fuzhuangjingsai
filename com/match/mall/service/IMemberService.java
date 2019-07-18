package com.match.mall.service;

import java.util.List;
import java.util.Map;

import com.match.common.result.ObjectResult;
import com.match.common.result.PageRequest;
import com.match.common.result.PageResult;
import com.match.mall.persist.Member;

public interface IMemberService {
	
	

	
	
	/**
	 * 获得单个user对象
	 * @param id
	 * @return
	 */
	public ObjectResult<Member> getItem(long id);
	
	
	/**
	 * 保存
	 * @param u
	 * @param lg
	 * @return
	 * @throws Exception
	 */
	public ObjectResult<Member> doSave(Member m) throws Exception;
	
	
	/**
	 * 刪除
	 * @param ids
	 * @param lg
	 * @return
	 * @throws Exception
	 */
	public ObjectResult<Boolean> doDel(Member m)throws Exception;
	
	
	/**
	 * 重置密码
	 * @param ids
	 * @param lg
	 * @return
	 * @throws Exception
	 */
	public ObjectResult<Boolean> resetPwd(Member m)throws Exception;
	
	
	
	/**
	 * 修改密码
	 * @param id
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	public ObjectResult<Member> doChangePassword(long id,String oldPwd,String newPwd)throws Exception;
	
	
	
	public ObjectResult<Member> doLogin(String password,String username);


	public PageResult<Map<String, Object>> getmemberList(Member member, PageRequest pr);


	public ObjectResult<Map<String, Object>> delMember(long id);
	
	
}
