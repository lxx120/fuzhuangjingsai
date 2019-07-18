package com.match.org.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.match.baciss.cl.UserChangLiang;
import com.match.baciss.mapper.UserMapper;
import com.match.baciss.model.User;
import com.match.common.BasicMethod;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.org.mapper.EnterpriseMapper;
import com.match.org.model.Enterprise;
import com.match.org.service.EnterpriseService;
import com.match.pinyin.utils.Pinyinmethod;
import com.match.proposition.mapper.CompetitionMapper;

@org.springframework.stereotype.Service
public class EnterpriseServiceImpl implements EnterpriseService {

	@Resource
	private  EnterpriseMapper  enterpriseMapper;
	
	@Resource
	private  UserMapper  UserMapper;
	
	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	
	@Override
	public int addEnterprise(Enterprise enterprise,String phone) throws Exception {
		
		//添加企业
		Map<String,Object> map = UserMapper.findUserExistByPhone(phone);
		if(map!=null)
		{
			return 2;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		enterprise.setMtime(sdf.parse(sdf.format(new Date())));
		enterpriseMapper.insertSelective(enterprise);
		if(0!=enterprise.getId())
		{
			//添加企业账号
			User user = new User();
			user.setEnterpriseid(enterprise.getId());
			user.setPhone(phone);
			user.setRealname(enterprise.getContactor());
			String  s = Pinyinmethod.ToPinyin(enterprise.getContactor());
			s = s+System.currentTimeMillis();
			user.setUsername(s);
			user.setWorker(1);
			user.setManager(1);
			user.setType(UserChangLiang.compant);
			user.setPassword(BasicMethod.encryptSHA1(UserChangLiang.password));
			user.setMtime(UserChangLiang.mtime());
			 UserMapper.addUser(user);
			 return 1;
		}
		return -1;
	}

	@Override
	public PageResult<Map<String,Object>> findEnterprisePage(Enterprise exEnterprise, PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>>  pr = new PageResult<Map<String,Object>>();
		int count=enterpriseMapper.count(exEnterprise);
		pr.setPages(Daomethod.countpages(count, pr.getPagesize()));
		pr.setRowCount(count);
		pr.setItems(enterpriseMapper.findEnterprisePage(exEnterprise.getName(), pageModel.getPage(), pageModel.getPagesize()));
		return pr;
	}

	@Override
	public Map<String, Object> findEnterpriseById(long id) throws Exception {
		// TODO Auto-generated method stub
		return enterpriseMapper.findEnterpriseById(id);
	}

	@Override
	public boolean deleteEnterprise(long id) throws Exception {
		// TODO Auto-generated method stub
		int i =  enterpriseMapper.deleteByPrimaryKey(id);
		if(i>0)
		{
			return  true;
		}
		return false;
	}

	@Override
	public int updateEnterprise(Enterprise enterprise,String phone,long userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map  = UserMapper.findUserPhone(phone, enterprise.getId());
		if(map!=null)
		{
			return  2;
		}
		int i = enterpriseMapper.updateByPrimaryKeySelective(enterprise);
		if(i>0)
		{
			//修改企业账号
			User user = new User();
			user.setEnterpriseid(enterprise.getId());
			user.setPhone(phone);
			user.setId(userid);
			user.setRealname(enterprise.getContactor());
			String  s = Pinyinmethod.ToPinyin(enterprise.getContactor());
			s = s+System.currentTimeMillis();
			user.setUsername(s);
			user.setWorker(1);
			user.setMtime(UserChangLiang.mtime());
			 UserMapper.updateUser(user);
			return  1;
		}
		return -1;
	}

	@Override
	public PageResult<Map<String, Object>> findDivisionToCompany(Enterprise enterprise, PageModel pageModel)
			throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>>  prr = new PageResult<Map<String,Object>>();
		//查询当期竞赛
		Map<String,Object> map = CompetitionMapper.findCurrnetCompetition2();
		if(map==null)
		{
			prr.setCode(1);
			prr.setErrmsg("暂无比赛进行");
			return prr;
		}
		long competitionid = Long.parseLong(map.get("id").toString());
		int count=enterpriseMapper.countfindDivisionToCompany(competitionid, enterprise.getId(), enterprise.getName());
		prr.setPages(Daomethod.countpages(count, prr.getPagesize()));
		prr.setRowCount(count);
		
		List<Map<String,Object>>  list = enterpriseMapper.findDivisionToCompany(competitionid, enterprise.getId(), enterprise.getName(), pageModel.getPage(), pageModel.getPagesize());
		prr.setItems(list);
		
		return prr;
	}

	@Override
	public Map<String, Object> findEnterpriseToTheme(long themeid) throws Exception {
		// TODO Auto-generated method stub
		return enterpriseMapper.findEnterpriseToTheme(themeid);
	}

	@Override
	public int updateEnterpriseWeb(Enterprise enterprise) throws Exception {
		// TODO Auto-generated method stub
		return enterpriseMapper.updateByPrimaryKeySelective(enterprise);
	}

	@Override
	public Map<String, Object> findEnterpriseByIdWeb(long id) throws Exception {
		// TODO Auto-generated method stub
		return enterpriseMapper.findEnterpriseByIdWeb(id);
	}

	@Override
	public PageResult<Map<String, Object>> findEnterpriseJoinComThemeWork(long divisionid,long id,PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=enterpriseMapper.countfindEnterpriseJoinComThemeWork(divisionid, id);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> ml = enterpriseMapper.findEnterpriseJoinComThemeWork(divisionid,id,pageModel.getPage(),pageModel.getPagesize());
		prr.setItems(ml);
		return prr;
	}

	@Override
	public List<Map<String, Object>> findEnterpriseByCompetitionid(long competitionid) throws Exception {
		// TODO Auto-generated method stub
		if(competitionid==0)
		{
			Map<String,Object> map = CompetitionMapper.findCurrnetCompetition3();
			if(map!=null)
			{
				competitionid = Integer.parseInt(map.get("id").toString());
			}
			else
			{
				return null;
			}
		}
		return enterpriseMapper.findEnterpriseByCompetitionid(competitionid);
	}


}
