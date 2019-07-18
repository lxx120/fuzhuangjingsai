package com.match.mall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.match.common.BasicMethod;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageRequest;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.mall.mapper.MemberMapper;
import com.match.mall.persist.Member;
import com.match.mall.service.IMemberService;
@Service
public class MemberService implements IMemberService {
	
	@Resource
	private MemberMapper memberper;

	@Override
	public ObjectResult<Member> getItem(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<Member> doSave(Member m) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<Boolean> doDel(Member m) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<Boolean> resetPwd(Member m) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<Member> doChangePassword(long id, String oldPwd, String newPwd) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<Member> doLogin(String password, String username) {
		ObjectResult<Member> orr=new ObjectResult<Member>();
		if(StringUtils.isEmpty(password)){
			orr.setCode(1);
			orr.setErrmsg("密码为空，请输入密码！");
			return orr;
		}
		if(StringUtils.isEmpty(username)){
			orr.setCode(1);
			orr.setErrmsg("账号为空，请输入登录账号！");
			return orr;
		}
		Member m= memberper.getByName(username);
		if(m==null){
			orr.setCode(1);
			orr.setErrmsg("账号错误，请输入正确的账号！");
			return orr;
		}
		if(!BasicMethod.encryptSHA(password).equals(m.getPassword())){
			orr.setCode(1);
			orr.setErrmsg("密码错误，请输入正确的密码！");
			return orr;
		}
		m.setPassword("");
		orr.setCode(0);
		orr.setErrmsg("登陆成功");
		orr.setItem(m);
		return orr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public PageResult<Map<String, Object>> getmemberList(Member member, PageRequest pr) {
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		if(pr==null){
			pr=new PageRequest(0,30,null);
			pr.setNeedPages(true);
		}
		if(pr.isNeedPages()){
			int count=memberper.count(member);
			prr.setPages(Daomethod.countpages(count, pr.getPageSize()));
			prr.setRowCount(count);
		}
		try{
			List<Map<String,Object>> ml = memberper.getmemberList(member.getUsername(),member.getPhone(),member.getMail(),member.getEnterprise(),pr.getStart(), pr.getPageSize());
			prr.setCode(0);
			prr.setItems(ml);
		}catch(Exception e){
			e.printStackTrace();
		}
		return prr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<Map<String, Object>> delMember(long id) {
		ObjectResult<Map<String, Object>>  orr = new ObjectResult<Map<String, Object>> ();
		if(0 == id) {
			orr.setCode(1);
			orr.setErrmsg("id不能为空");
		}
		Member member = memberper.getById(id);
		if(member!=null) {
			memberper.delete(id);
			orr.setCode(0);
			orr.setErrmsg("删除成功");
		}else {
			orr.setCode(1);
			orr.setErrmsg("对象不存在");
		}
		return orr;
	}

}
