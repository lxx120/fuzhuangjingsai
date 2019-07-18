package com.match.proposition.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.baciss.mapper.UserMapper;
import com.match.baciss.model.User;
import com.match.common.BasicMethod;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.pinyin.utils.Pinyinmethod;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.DivisionMapper;
import com.match.proposition.model.Division;
import com.match.proposition.service.DivisionService;

@Service
public class DivisionServiceImpl implements DivisionService {

	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	@Autowired
	private  UserMapper  UserMapper;
	@Autowired
	private  DivisionMapper  DivisionMapper;
	
	@Override
	public int addDivision(Division division) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  amp = CompetitionMapper.findCurrnetCompetition3();
		if(amp==null)
		{
			return 2; //没有比赛
		}
		Map<String, Object> mapuser = UserMapper.findUserExistByPhone(division.getPhone());
		if (mapuser != null) {
			division.setUserid(Long.parseLong(mapuser.get("id").toString()));
		} else {
			// 添加用户
			User user = new User();
			user.setPhone(division.getPhone());
			user.setRealname(division.getContator());
			String s = Pinyinmethod.ToPinyin(division.getContator());
			s = s + System.currentTimeMillis();
			user.setUsername(s);
			user.setMtime(UserChangLiang.mtime());
			user.setPassword(BasicMethod.encryptSHA1(UserChangLiang.password));
			UserMapper.addUser(user);
			division.setUserid(user.getId());
		}
		division.setMtime(UserChangLiang.mtime());
		division.setCompetitionid(Long.parseLong(amp.get("id").toString()));
		
		int i = DivisionMapper.insertSelective(division);
		if(i>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public int updateDivision(Division division) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  amp = CompetitionMapper.findCurrnetCompetition3();
		if(amp==null)
		{
			return 2; //没有比赛
		}
		Map<String, Object> mapuser = UserMapper.findUserExistByPhone(division.getPhone());
		if (mapuser != null) {
			division.setUserid(Long.parseLong(mapuser.get("id").toString()));
		} else {
			// 添加用户
			User user = new User();
			user.setPhone(division.getPhone());
			user.setRealname(division.getContator());
			String s = Pinyinmethod.ToPinyin(division.getContator());
			s = s + System.currentTimeMillis();
			user.setUsername(s);
			user.setMtime(UserChangLiang.mtime());
			user.setPassword(BasicMethod.encryptSHA1(UserChangLiang.password));
			UserMapper.addUser(user);
			division.setUserid(user.getId());
		}
		division.setMtime(UserChangLiang.mtime());
		division.setCompetitionid(Long.parseLong(amp.get("id").toString()));
		
		int i = DivisionMapper.updateByPrimaryKeySelective(division);
		if(i>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public PageResult<Map<String, Object>> findDivisionPage(String name, PageModel pageModel)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  amp = CompetitionMapper.findCurrnetCompetition3();
		String comid = amp.get("id").toString();
		PageResult<Map<String, Object>> page = new PageResult<Map<String, Object>>();
		int count=DivisionMapper.countfindDivisionPage(name, comid);
		page.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		page.setRowCount(count);
		
		List<Map<String,Object>>  list = DivisionMapper.findDivisionPage(name, comid, pageModel.getPage(), pageModel.getPagesize());
		page.setItems(list);
		return page;
	}

	@Override
	public Map<String, Object> findDivisionById(long id) throws Exception {
		// TODO Auto-generated method stub
		
		return DivisionMapper.findDivisionById(id);
	}

	@Override
	public boolean deleteDivision(long id) throws Exception {
		// TODO Auto-generated method stub
		int i =  DivisionMapper.deleteByPrimaryKey(id);
		if(i>0)
		{
			return true;
		}
		return   false;
	}

}
