package com.match.baciss.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.baciss.mapper.JudgeDivisionMapper;
import com.match.baciss.mapper.JudgeInformationMapper;
import com.match.baciss.mapper.UserMapper;
import com.match.baciss.model.JudgeDivision;
import com.match.baciss.model.JudgeInformation;
import com.match.baciss.model.User;
import com.match.baciss.service.JudgeInformationService;
import com.match.common.BasicMethod;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.pinyin.utils.Pinyinmethod;
import com.match.proposition.mapper.DivisionCompanyMapper;
import com.match.proposition.mapper.DivisionSchoolMapper;

@Service
public class JudgeInformationServiceImpl implements JudgeInformationService {

	@Autowired
	private JudgeInformationMapper judgeInformationMapper;

	@Autowired
	private JudgeDivisionMapper judgeDivisionMapper;

	@Autowired
	private UserMapper usermapper;
	
	@Autowired
	private  DivisionSchoolMapper  DivisionSchoolMapper;
	
	@Autowired
	private  DivisionCompanyMapper  DivisionCompanyMapper;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public int addJudgeInformation(JudgeInformation judgeInformation, long diid) throws Exception {

		// 设置时间
		judgeInformation.setMtime(UserChangLiang.mtime());
		judgeInformation.setDivisionid(diid);
		JudgeDivision judgeDivision = new JudgeDivision();
		judgeDivision.setMtime(UserChangLiang.mtime());

		// 查询用户是否为评委
		Map<String, Object> map = judgeInformationMapper.findJudgeInformation(judgeInformation.getUserid());
		// 如果用户是评委
		if (map != null) {
			// 评委等级 1全局 2赛区
			int levelaccess = Integer.parseInt(map.get("levelAccess").toString());
			if (diid != 0 && levelaccess == 1) {
				return 2; // 此用户为全局评为，无需绑定，直接使用
			} else if (diid != 0 && levelaccess != 1) {
				// 用户是评委直接绑定
				judgeDivision.setDivisionid(diid);
				judgeDivision.setJudgeid(Long.parseLong(map.get("id").toString()));
				int i = judgeDivisionMapper.insertSelective(judgeDivision);
				if (i > 0) {
					return 1; // 绑定成功
				}
				return -1; // 绑定失败
			} else if (diid == 0 && levelaccess != 1) {
				// 用户不是全局评为，但是系统管理员添加此用户为评委
				judgeInformation.setId(Long.parseLong(map.get("id").toString()));
				judgeInformation.setLevelAccess(1);
				int i  = judgeInformationMapper.updateByPrimaryKeySelective(judgeInformation);
				if(i>0)
				{
					return 1;
				}
				return -1;
			}

		}
		// 用户不是评委，去添加成为评委
		else {
			int levelaccess = 0;
			if (diid != 0) {
				// 分赛区绑定
				levelaccess = 2;
				// 添加到评委库
				judgeInformation.setLevelAccess(levelaccess);
				int i = judgeInformationMapper.insertSelective(judgeInformation);

				// 添加到赛区评委表
				judgeDivision.setDivisionid(diid);
				judgeDivision.setJudgeid(judgeInformation.getId());
				int j = judgeDivisionMapper.insertSelective(judgeDivision);
				if (i > 0 && j > 0) {
					return 1;
				}
				return -1;
			} else {
				// 总赛区绑定
				levelaccess = 1;
				judgeInformation.setLevelAccess(levelaccess);
				int i = judgeInformationMapper.insertSelective(judgeInformation);
				if (i > 0) {
					return 1;
				}
				return -1;
			}

		}

		return -1;

	}

	@Override
	public boolean deleteJudgeInformation(long id) throws Exception {
		// TODO Auto-generated method stub
		int i = judgeInformationMapper.deleteByPrimaryKey(id);
		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateJudgeInformation(JudgeInformation judgeInformation) throws Exception {
		// TODO Auto-generated method stub
		int i = judgeInformationMapper.updateByPrimaryKeySelective(judgeInformation);
		if (i > 0) {
			return true;
		}
		return false;
	}

	@Override
	public PageResult<Map<String, Object>> findJudgeInformationPage(JudgeInformation judgeInformation,
			PageModel pageModel, long diid) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String, Object>> rp = new PageResult<Map<String, Object>>();
		int count = judgeInformationMapper.count(judgeInformation);
		rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		rp.setRowCount(count);
		List<Map<String, Object>> list = judgeInformationMapper.findJUdgeInformationPage(diid,
				judgeInformation.getJudgename(), pageModel.getPage(), pageModel.getPagesize());
		rp.setItems(list);
		return rp;
	}

	@Override
	public Map<String, Object> findJudgeInformationById(long id) throws Exception {
		// TODO Auto-generated method stub

		return judgeInformationMapper.findJudgeInformationById(id);
	}

	@Override
	public PageResult<Map<String, Object>> findGroupToJudgeInformationPage(JudgeInformation judgeInformation,
			PageModel pageModel, long diid, long groupid) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String, Object>> rp = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//总赛查询绑定的评委
		if(diid==0)
		{
			int count = judgeInformationMapper.Groupcount(judgeInformation.getDivisionid(), judgeInformation.getJudgename(),
					groupid, diid);
			rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			rp.setRowCount(count);
			list = judgeInformationMapper.findGroupToJUdgeInformationPage(diid,
					judgeInformation.getJudgename(), pageModel.getPage(), pageModel.getPagesize(), groupid);
		}
		else
			//查询赛区剩余的评委
		{
			int count = judgeInformationMapper.DivisionGroupcount(judgeInformation.getDivisionid(), judgeInformation.getJudgename(),
					groupid, diid);
			rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			rp.setRowCount(count);
			list = judgeInformationMapper.findDivisionGroupToJUdgeInformationPage(diid,
					judgeInformation.getJudgename(), pageModel.getPage(), pageModel.getPagesize(), groupid);
		}
		rp.setItems(list);
		return rp;
	}

	@Override
	public PageResult<Map<String, Object>> findStaffToJudge(long divisionid, String name, int org, String realname,
			long orgid, PageModel pageModel) throws Exception {
		PageResult<Map<String, Object>> rp = new PageResult<Map<String, Object>>();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 总赛区
		if (divisionid == 0) {
			// 学校的
			if (1 == org) {
				int count = judgeInformationMapper.countManagerNoChooseSchoolJudge(realname, name);
				rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
				rp.setRowCount(count);
				list = judgeInformationMapper.findManagerNoChooseSchoolJudge(realname, name, pageModel.getPage(),
						pageModel.getPagesize());
			}
			// 企业的
			else {
				int count = judgeInformationMapper.countManagerNoChooseCompanyJudge(realname, name);
				rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
				rp.setRowCount(count);
				list = judgeInformationMapper.findManagerNoChooseCompanyJudge(realname, name, pageModel.getPage(),
						pageModel.getPagesize());
			}
		}
		// 赛区的
		else {
			// 学校的
			if (1 == org) {
				List<Map<String,Object>> schoollist = DivisionSchoolMapper.findSchoolByDivisionID(divisionid);
				if(schoollist!=null && schoollist.size()>0)
				{
					String pinjie = "";
					for (Map<String, Object> map : schoollist) {
						pinjie = pinjie+"'"+map.get("schoolid").toString()+"',";
					}
					
					int count = judgeInformationMapper.countDivisionNoChooseSchoolJudge(pinjie.substring(0, pinjie.length()-1),divisionid, realname, name);
					rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
					rp.setRowCount(count);
					list = judgeInformationMapper.findDivisionNoChooseSchoolJudge(pinjie.substring(0, pinjie.length()-1),divisionid, realname, name,
							pageModel.getPage(), pageModel.getPagesize());
				}
				else
				{
					list = null;
				}
				
			}
			// 企业的
			else {
				
				List<Map<String,Object>>  comlist = DivisionCompanyMapper.findCompanyByDivisionID(divisionid);
				if(comlist!=null && comlist.size()>0)
				{
					String pinjie = "";
					for (Map<String, Object> map : comlist) {
						pinjie = pinjie+"'"+map.get("companyid").toString()+"',";
					}
					int count = judgeInformationMapper.countDivisionNoChooseCompanyJudge(pinjie.substring(0, pinjie.length()-1),divisionid, realname, name);
					rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
					rp.setRowCount(count);
					list = judgeInformationMapper.findDivisionNoChooseCompanyJudge(pinjie.substring(0, pinjie.length()-1),divisionid, realname, name,
							pageModel.getPage(), pageModel.getPagesize());
				}
				else
				{
					list=null;
				}
			}
		}
		
		if(list!=null && list.size()>0)
		{
			for (Map<String, Object> map : list) {
				map.put("status",2);
			}
		}
		rp.setItems(list);
		return rp;
	}

	@Override
	public PageResult<Map<String, Object>> findChooseStaff(long divisionid, String name, int org, String realname,
			long orgid, PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String, Object>> rp = new PageResult<Map<String, Object>>();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 总赛区
		if (divisionid == 0) {
			// 学校的
			if (1 == org) {
				int count = judgeInformationMapper.countManagerChooseSchoolJudge(realname, realname);
				rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
				rp.setRowCount(count);
				list = judgeInformationMapper.findManagerChooseSchoolJudge(realname, realname, pageModel.getPage(),
						pageModel.getPagesize());
			}
			// 企业的
			else {
				int count = judgeInformationMapper.countManagerChooseCompanyJudge(realname, realname);
				rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
				rp.setRowCount(count);
				list = judgeInformationMapper.findManagerChooseCompanyJudge(realname, realname, pageModel.getPage(),
						pageModel.getPagesize());
			}
		}
		// 赛区的
		else {
			// 学校的
			if (1 == org) {
				int count = judgeInformationMapper.countDivisionChooseSchoolJudge(divisionid, realname, realname);
				rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
				rp.setRowCount(count);
				list = judgeInformationMapper.findDivisionChooseSchoolJudge(divisionid, realname, realname,
						pageModel.getPage(), pageModel.getPagesize());
			}
			// 企业的
			else {
				int count = judgeInformationMapper.countDivisionChooseCompanyJudge(divisionid, realname, realname);
				rp.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
				rp.setRowCount(count);
				list = judgeInformationMapper.findDivisionChooseCompanyJudge(divisionid, realname, realname,
						pageModel.getPage(), pageModel.getPagesize());
			}
		}
		
		if(list!=null && list.size()>0)
		{
			for (Map<String, Object> map : list) {
				map.put("status",1);
			}
		}
		rp.setItems(list);
		return rp;
	}

	@Override
	public Map<String, Object> findJudgeInformationByUserid(long userid) throws Exception {
		// TODO Auto-generated method stub
		return judgeInformationMapper.findJudgeInformationByUserid(userid);
	}

	@Override
	public int addJudgeInformation1(JudgeInformation judgeInformation) throws Exception {
		// TODO Auto-generated method stub
		judgeInformation.setMtime(UserChangLiang.mtime());
		int i = judgeInformationMapper.insertSelective(judgeInformation);
		if(i>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public int addJudgeInformationNO(JudgeInformation judgeInformation, User user) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object>  mapphone = usermapper.findUserExistByPhone(user.getPhone());
		if(mapphone!=null)
		{
			return 2; //手机号已经存在
		}
		String s =  UUID.randomUUID().toString().replaceAll("-", "");
		user.setUsername(s);
		user.setMtime(UserChangLiang.mtime());
		user.setType(UserChangLiang.others);
		user.setPassword(BasicMethod.encryptSHA1(UserChangLiang.password));
		usermapper.addUser(user);
		
		// 设置时间
		judgeInformation.setMtime(UserChangLiang.mtime());
		JudgeDivision judgeDivision = new JudgeDivision();
		judgeInformation.setJudgetype(2);
		judgeDivision.setMtime(UserChangLiang.mtime());
		// 查询用户是否为评委
		Map<String, Object> map = judgeInformationMapper.findJudgeInformation(user.getId());
		// 如果用户是评委
				if (map != null) {
					// 评委等级 1全局 2赛区
					int levelaccess = Integer.parseInt(map.get("levelAccess").toString());
					if (judgeInformation.getDivisionid() != 0 && levelaccess == 1) {
						return 3; // 此用户为全局评为，无需绑定，直接使用
					} else if (judgeInformation.getDivisionid() != 0 && levelaccess != 1) {
						// 用户是评委直接绑定
						judgeDivision.setDivisionid(judgeInformation.getDivisionid());
						judgeDivision.setJudgeid(Long.parseLong(map.get("id").toString()));
						int i = judgeDivisionMapper.insertSelective(judgeDivision);
						if (i > 0) {
							return 1; // 绑定成功
						}
						return -1; // 绑定失败
					} else if (judgeInformation.getDivisionid() == 0 && levelaccess != 1) {
						// 用户不是全局评为，但是系统管理员添加此用户为评委
						judgeInformation.setId(Long.parseLong(map.get("id").toString()));
						judgeInformation.setLevelAccess(1);
						judgeInformationMapper.updateByPrimaryKeySelective(judgeInformation);
					}

				}
				// 用户不是评委，去添加成为评委
				else {
					int levelaccess = 0;
					if (judgeInformation.getDivisionid() != 0) {
						// 分赛区绑定
						levelaccess = 2;
						// 添加到评委库
						judgeInformation.setLevelAccess(levelaccess);
						int i = judgeInformationMapper.insertSelective(judgeInformation);

						// 添加到赛区评委表
						judgeDivision.setDivisionid(judgeInformation.getDivisionid());
						judgeDivision.setJudgeid(judgeInformation.getId());
						int j = judgeDivisionMapper.insertSelective(judgeDivision);
						if (i > 0 && j > 0) {
							return 1;
						}
						return -1;
					} else {
						// 总赛区绑定
						levelaccess = 1;
						judgeInformation.setLevelAccess(levelaccess);
						int i = judgeInformationMapper.insertSelective(judgeInformation);
						if (i > 0) {
							return 1;
						}
						return -1;
					}

				}

				return -1;
	}
}
