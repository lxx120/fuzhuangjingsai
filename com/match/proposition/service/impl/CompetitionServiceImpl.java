package com.match.proposition.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.match.baciss.cl.UserChangLiang;
import com.match.baciss.mapper.UserMapper;
import com.match.baciss.model.User;
import com.match.common.BasicMethod;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.judges.mapper.AssessRoundsMapper;
import com.match.judges.mapper.CompetitionAprizeMapper;
import com.match.judges.service.CompetitionAprizeService;
import com.match.pinyin.utils.Pinyinmethod;
import com.match.proposition.cl.ComChangLiang;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.DivisionMapper;
import com.match.proposition.mapper.ThemeWorkMapper;
import com.match.proposition.model.Competition;
import com.match.proposition.service.CompetitionService;
import com.match.reviewresult.mapper.CollegeScoreMapper;
import com.match.reviewresult.mapper.DepartmentScoreMapper;
import com.match.reviewresult.mapper.ExcellentCollegeMapper;
import com.match.reviewresult.mapper.ExcellentConfigureMapper;
import com.match.reviewresult.mapper.ExcellentPartmentMapper;
import com.match.reviewresult.mapper.ExcellentPersonMapper;
import com.match.reviewresult.mapper.ExcellentTeacherMapper;
import com.match.reviewresult.mapper.PersonalScoreMapper;
import com.match.reviewresult.mapper.TeacherScoreMapper;
import com.match.reviewresult.model.CollegeScore;
import com.match.reviewresult.model.DepartmentScore;
import com.match.reviewresult.model.ExcellentCollege;
import com.match.reviewresult.model.ExcellentPartment;
import com.match.reviewresult.model.ExcellentPerson;
import com.match.reviewresult.model.ExcellentTeacher;
import com.match.reviewresult.model.PersonalScore;
import com.match.reviewresult.model.TeacherScore;

@Service
public class CompetitionServiceImpl implements CompetitionService {

	@Autowired
	private CompetitionMapper CompetitionMapper;

	@Autowired
	private AssessRoundsMapper AssessRoundsMapper;

	@Autowired
	private DivisionMapper DivisionMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ThemeWorkMapper ThemeWorkMapper;
	
	@Autowired
	private  PersonalScoreMapper  PersonalScoreMapper;
	
	@Autowired
	private  TeacherScoreMapper  TeacherScoreMapper;
	
	@Autowired
	private  CollegeScoreMapper  CollegeScoreMapper;
	
	@Autowired
	private  DepartmentScoreMapper  DepartmentScoreMapper;
	
	@Autowired
	private  ExcellentTeacherMapper  ExcellentTeacherMapper;
	
	@Autowired
	private  ExcellentCollegeMapper  ExcellentCollegeMapper;
	
	@Autowired
	private  ExcellentPersonMapper ExcellentPersonMapper;
	
	@Autowired
	private  ExcellentPartmentMapper  ExcellentPartmentMapperl;
	
	@Autowired
	private  ExcellentConfigureMapper  ExcellentConfigureMapper;

	@Autowired
	private  CompetitionAprizeMapper  CompetitionAprizeMapper;
	
	@Autowired
	private  CompetitionAprizeService  CompetitionAprizeService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, RuntimeException.class })
	public int addCompetition(Competition competition, List<Map<String, Object>> list1) throws Exception {

		Map<String, Object> mapaa = CompetitionMapper.findCurrnetCompetition3();
		if (mapaa != null) {
			return 0;
		}
		//
		int i1 = 1;
		int i2 = 1;
		// 添加竞赛
		competition.setStatus(ComChangLiang.ready);
		competition.setMtime(UserChangLiang.mtime());
		int i = CompetitionMapper.insertSelective(competition);
		if (i > 0) {
//			if (list != null && list.size() > 0) {
//				// 批量添加轮次
//				long id = 0;
//				int z = 1;
//				for (Map<String, Object> map : list) {
//					AssessRounds assessRounds = new AssessRounds();
//					assessRounds.setMtime(UserChangLiang.mtime());
//					assessRounds.setAhid((long)1);
//					assessRounds.setCompetitionid(competition.getId());
//					assessRounds.setArtype(Integer.parseInt(map.get("artype").toString()));
//					assessRounds.setIschoose(-1);
//					assessRounds.setIsSchool(0);
//					assessRounds.setDivisionid((long)0);
//					assessRounds.setReviewstime(map.get("reviewstime").toString());
//					assessRounds.setReviewetime(map.get("reviewetime").toString());
//					assessRounds.setLevel(z);
//					assessRounds.setPre(id);
//					AssessRoundsMapper.insertSelective(assessRounds);
//					z=z+1;
//					id=assessRounds.getId();
//				}
//			}
			if (list1 != null && list1.size() > 0) {
				// 批量添加赛区
				for (Map<String, Object> map : list1) {
					map.put("mtime", UserChangLiang.mtime());
					map.put("competitionid", competition.getId());

					// 查询手机号是否存在
					String zda = map.get("phone").toString();
					Map<String, Object> mapuser = userMapper.findUserExistByPhone(map.get("phone").toString());
					if (mapuser != null) {
						map.put("userid", (long) Integer.parseInt(mapuser.get("id").toString()));
					} else {
						// 添加用户
						User user = new User();
						user.setPhone(map.get("phone").toString());
						user.setType(UserChangLiang.division);
						user.setRealname(map.get("contator").toString());
						String s = Pinyinmethod.ToPinyin(map.get("contator").toString());
						s = s + System.currentTimeMillis();
						user.setUsername(s);
						user.setMtime(UserChangLiang.mtime());
						user.setPassword(BasicMethod.encryptSHA1(UserChangLiang.password));
						user.setManager(1);
						userMapper.addUser(user);
						map.put("userid", user.getId());
					}
				}
				i2 = DivisionMapper.insetList(list1);
			}

			if (i > 0 && i1 > 0 && i2 > 0) {
				return 1;
			}
		}
		return -1;
	}

	@Override
	public int updateCompetition(Competition competition) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = CompetitionMapper.findCurrnetCompetitionTime(competition.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = sdf.parse(sdf.format(new Date()));
		if (map != null) {
			Date stime = sdf.parse(map.get("stime").toString());
			Date etime = sdf.parse(map.get("etime").toString());

			if (s1.compareTo(etime) <= 0 && s1.compareTo(stime) >= 0) {
				return 2; // 比赛期间不能修改比赛
			}
			if (s1.compareTo(etime) > 0) {
				return 3; // 比赛结束不能修改
			}
		}
		int i = CompetitionMapper.updateByPrimaryKeySelective(competition);
		if (i > 0) {
			return 1;
		}
		return -1;
	}

	@Override
	public int deleteCompetition(long id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = CompetitionMapper.findCurrnetCompetitionTime(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = sdf.parse(sdf.format(new Date()));
		if (map != null) {
			Date stime = sdf.parse(map.get("stime").toString());
			Date etime = sdf.parse(map.get("etime").toString());

			if (s1.compareTo(etime) <= 0 && s1.compareTo(stime) >= 0) {
				return 2; // 比赛期间不能删除比赛
			}
			if (s1.compareTo(etime) > 0) {
				return 3; // 比赛结束不能删除
			}
		}
		int i = CompetitionMapper.deleteByPrimaryKey(id);
		if (i > 0) {
			return 1;
		}
		return -1;
	}

	@Override
	public PageResult<Map<String, Object>> findCompetitionPage(Competition competition, PageModel pageModel)
			throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PageResult<Map<String, Object>> prr = new PageResult<Map<String, Object>>();
		int count = CompetitionMapper.count(competition);
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String, Object>> list = CompetitionMapper.findCompetitionPage(competition.getName(),
				pageModel.getPage(), pageModel.getPagesize());
//		if (list != null && list.size() > 0) {
//			for (Map<String, Object> map : list) {
//				Date s = sdf.parse(map.get("etime").toString());
//				Date s1 = sdf.parse(sdf.format(new Date()));
//				int i = s.compareTo(s1);
//				if(i>0)
//				{
//					map.put("sstatus",1);
//				}
//			}
//		}
		prr.setItems(list);
		return prr;
	}

	@Override
	public Map<String, Object> findCompetitionById(long id) throws Exception {
		// TODO Auto-generated method stub
		return CompetitionMapper.findCompetitionById(id);
	}

	@Override
	public List<Map<String, Object>> fidnAllCompetition() throws Exception {
		// TODO Auto-generated method stub
		return CompetitionMapper.fidnAllCompetition();
	}

	@Override
	public boolean updateCompetitionStatus() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = CompetitionMapper.findCurrnetCompetition3();
		if (map != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date s1 = sdf.parse(sdf.format(new Date()));
			Date etime = sdf.parse(map.get("etime").toString());
			Date stime = sdf.parse(map.get("stime").toString());
			if (0 == Integer.parseInt(map.get("status").toString())) {
				if (s1.compareTo(stime) >= 0) {
					Competition competition = new Competition();
					competition.setId(Long.parseLong(map.get("id").toString()));
					competition.setStatus(ComChangLiang.start);
					competition.setMtime(UserChangLiang.mtime());
					int i = CompetitionMapper.updateByPrimaryKeySelective(competition);
					if (i > 0) {
						return true;
					}
				}
			} else if (1 == Integer.parseInt(map.get("status").toString())) {
				if (s1.compareTo(etime) >= 0) {
					Competition competition = new Competition();
					competition.setId(Long.parseLong(map.get("id").toString()));
					competition.setStatus(ComChangLiang.close);
					competition.setMtime(UserChangLiang.mtime());
					// 修改比赛状态为结束
					int i = CompetitionMapper.updateByPrimaryKeySelective(competition);
					if (i > 0) {
						// 算积分
						ScoreSum(Long.parseLong(map.get("id").toString()));
						return true;
					}
				}
			}
		}
		return false;

	}

	public void ScoreSum(long competationid) throws Exception {
		// 先查询整个比赛配置为获奖的轮次的配置信息id和积分
		List<Map<String, Object>> arlist = AssessRoundsMapper.findAllARHuoJiang(competationid);
		if (arlist != null && arlist.size() > 0) {
			// 把获奖id放到集合里面
			List<String> listprice = new ArrayList<String>();
			for (Map<String, Object> map : arlist) {
				listprice.add(map.get("id").toString());
			}
			if (listprice != null && listprice.size() > 0) {
				// 去查询获奖作品(最终获奖id，用户id,老师手机号，学校id，院系id)
				List<Map<String, Object>> infolist = ThemeWorkMapper.findPriceThemeWork(listprice);
				if (infolist != null && infolist.size() > 0) {
					List<PersonalScore> student = new ArrayList<PersonalScore>();
					List<TeacherScore> teacehr = new ArrayList<TeacherScore>();
					List<CollegeScore> college = new ArrayList<CollegeScore>();
					List<DepartmentScore> department = new ArrayList<DepartmentScore>();
					for (Map<String, Object> map : infolist) {
						for (Map<String, Object> map2 : arlist) {
							if (map.get("finalAprizeid").toString().equals(map2.get("id").toString())) {

								// 学生
								if (student.size() == 0) {
									PersonalScore personalScore = new PersonalScore();
									personalScore.setScore(Integer.valueOf(map2.get("score").toString()));
									personalScore.setUserid(Long.parseLong(map.get("userid").toString()));
									personalScore.setCompetationid(competationid);
									student.add(personalScore);
								} else {
									int l = 0;
									for (PersonalScore personalScore : student) {
										if (personalScore.getUserid()==Long.parseLong(map.get("userid").toString())) {
											personalScore.setScore(personalScore.getScore()
													+ Integer.valueOf(map2.get("score").toString()));
											l = 1;
											break;
										}
									}
									if (l == 0) {
										PersonalScore personalScore = new PersonalScore();
										personalScore.setScore(Integer.valueOf(map2.get("score").toString()));
										personalScore.setUserid(Long.parseLong(map.get("userid").toString()));
										personalScore.setCompetationid(competationid);
										student.add(personalScore);
									}

								}

								// 老师

								if (teacehr.size() == 0) {
									TeacherScore teacherScore = new TeacherScore();
									teacherScore.setScore(Integer.valueOf(map2.get("score").toString()));
									teacherScore.setPhone(map.get("teacherphone").toString());
									teacherScore.setCompetationid(competationid);
									teacehr.add(teacherScore);
								} else {
									int l = 0;
									for (TeacherScore teacherScore : teacehr) {
										if (teacherScore.getPhone().equals(map.get("teacherphone").toString())) {
											teacherScore.setScore(teacherScore.getScore()
													+ Integer.valueOf(map2.get("score").toString()));
											l = 1;
											break;
										}
									}
									if (l == 0) {
										TeacherScore teacherScore = new TeacherScore();
										teacherScore.setScore(Integer.valueOf(map2.get("score").toString()));
										teacherScore.setPhone(map.get("teacherphone").toString());
										teacherScore.setCompetationid(competationid);
										teacherScore.setCompetationid(competationid);
										teacehr.add(teacherScore);
									}

								}
								// 学校

								if (college.size() == 0) {
									CollegeScore collegeScore = new CollegeScore();
									collegeScore.setScore(Integer.valueOf(map2.get("score").toString()));
									collegeScore.setCollegeid(Long.parseLong(map.get("college").toString()));
									collegeScore.setCompetationid(competationid);
									college.add(collegeScore);
								} else {
									int l = 0;
									for (CollegeScore collegeScore : college) {
										if (collegeScore.getCollegeid()==Long.parseLong(map.get("college").toString())) {
											collegeScore.setScore(collegeScore.getScore()
													+ Integer.valueOf(map2.get("score").toString()));
											l = 1;
											break;
										}
									}
									if (l == 0) {
										CollegeScore collegeScore = new CollegeScore();
										collegeScore.setScore(Integer.valueOf(map2.get("score").toString()));
										collegeScore.setCollegeid(Long.parseLong(map.get("college").toString()));
										collegeScore.setCompetationid(competationid);
										college.add(collegeScore);
									}

								}
								// 院系

								if ( department.size() == 0) {
									DepartmentScore departmentScore = new DepartmentScore();
									departmentScore.setScore(Integer.valueOf(map2.get("score").toString()));
									departmentScore.setDepartmentid(Long.parseLong(map.get("department").toString()));
									departmentScore.setCompetationid(competationid);
									department.add(departmentScore);
								} else {
									int l = 0;
									for (DepartmentScore departmentScore : department) {
										if (departmentScore.getDepartmentid()==Long.parseLong(map.get("department").toString())) {
											departmentScore.setScore(departmentScore.getScore()
													+ Integer.valueOf(map2.get("score").toString()));
											l = 1;
											break;
										}
									}
									if (l == 0) {
										DepartmentScore departmentScore = new DepartmentScore();
										departmentScore.setScore(Integer.valueOf(map2.get("score").toString()));
										departmentScore
												.setDepartmentid(Long.parseLong(map.get("department").toString()));
										departmentScore.setCompetationid(competationid);
										department.add(departmentScore);
									}

								}
							}
						}
					}

					// 排序
					Collections.sort(student, new Comparator<PersonalScore>() {

						@Override
						public int compare(PersonalScore o1, PersonalScore o2) {
							// TODO Auto-generated method stub
							int dff = o2.getScore() - o1.getScore();
							if (dff > 0) {
								return 1;
							} else if (dff < 0) {
								return -1;
							}
							return 0;
						}
					});

					Collections.sort(teacehr, new Comparator<TeacherScore>() {

						@Override
						public int compare(TeacherScore o1, TeacherScore o2) {
							// TODO Auto-generated method stub
							int dff = o2.getScore() - o1.getScore();
							if (dff > 0) {
								return 1;
							} else if (dff < 0) {
								return -1;
							}
							return 0;
						}
					});

					Collections.sort(department, new Comparator<DepartmentScore>() {

						@Override
						public int compare(DepartmentScore o1, DepartmentScore o2) {
							// TODO Auto-generated method stub
							int dff = o2.getScore() - o1.getScore();
							if (dff > 0) {
								return 1;
							} else if (dff < 0) {
								return -1;
							}
							return 0;
						}
					});

					Collections.sort(college, new Comparator<CollegeScore>() {

						@Override
						public int compare(CollegeScore o1, CollegeScore o2) {
							// TODO Auto-generated method stub
							int dff = o2.getScore() - o1.getScore();
							if (dff > 0) {
								return 1;
							} else if (dff < 0) {
								return -1;
							}
							return 0;
						}
					});

					// 批量添加到积分表
					// 批量添加到优秀表
					addBatchScore(student, teacehr, college, department, competationid);
				}
			}
		}
	}
	
	public  int  addBatchScore(List<PersonalScore> student,List<TeacherScore> teacehr,List<CollegeScore> college,List<DepartmentScore> department,long competationid)  throws   Exception
	{
		List<ExcellentPerson>  listpe = new ArrayList<ExcellentPerson>();
		List<ExcellentTeacher>  listte = new ArrayList<ExcellentTeacher>();
		List<ExcellentCollege>  listco = new ArrayList<ExcellentCollege>();
		List<ExcellentPartment>  listde = new ArrayList<ExcellentPartment>();
		int i =1;
		int i1 =1;
		int i2 =1;
		int i3=1;
		for (DepartmentScore departmentScore : department) {
			departmentScore.setMtime(UserChangLiang.mtime());
			ExcellentPartment excellentPartment = new ExcellentPartment();
			excellentPartment.setMtime(UserChangLiang.mtime());
			excellentPartment.setTotalcsore(departmentScore.getScore());
			excellentPartment.setCompetationid(competationid);
			excellentPartment.setPartmentid(departmentScore.getDepartmentid());
			excellentPartment.setRanking(i);
			listde.add(excellentPartment);
			i=i+1;
		}
		for (PersonalScore personalScore : student) {
			personalScore.setMtime(UserChangLiang.mtime());
			ExcellentPerson excellentPerson = new ExcellentPerson();
			excellentPerson.setUserid(personalScore.getUserid());
			excellentPerson.setCompetationid(competationid);
			excellentPerson.setMtime(UserChangLiang.mtime());
			excellentPerson.setTotalcsore(personalScore.getScore());
			excellentPerson.setRanking(i1);
			listpe.add(excellentPerson);
			i1 = i1+1;
		}
		for (TeacherScore teacherScore : teacehr) {
			teacherScore.setMtime(UserChangLiang.mtime());
			ExcellentTeacher excellentTeacher = new ExcellentTeacher();
			excellentTeacher.setPhone(teacherScore.getPhone());
			excellentTeacher.setCompetationid(competationid);
			excellentTeacher.setMtime(UserChangLiang.mtime());
			excellentTeacher.setTotalcsore(teacherScore.getScore());
			excellentTeacher.setRanking(i2);
			excellentTeacher.setType(2);//自动
			listte.add(excellentTeacher);
			i2 = i2+1;
		}
		for (CollegeScore collegeScore : college) {
			collegeScore.setMtime(UserChangLiang.mtime());
			ExcellentCollege excellentCollege = new ExcellentCollege();
			excellentCollege.setCollegeid(collegeScore.getCollegeid());
			excellentCollege.setCompetationid(competationid);
			excellentCollege.setMtime(UserChangLiang.mtime());
			excellentCollege.setTotalcsore(collegeScore.getScore());
			excellentCollege.setRanking(i3);
			listco.add(excellentCollege);
			i3 = i3+1;
		}
		PersonalScoreMapper.addBatchPersonalScore(student);
		DepartmentScoreMapper.addBatchDepartmentScore(department);
		TeacherScoreMapper.addBatchTeacherScore(teacehr);
		CollegeScoreMapper.addBatchCollegeScore(college);
		
		Map<String,Object> map = ExcellentConfigureMapper.findExcellentConfigure(competationid);
		int excellentperson = Integer.parseInt(map.get("excellentperson").toString());
		int excellentteacher = Integer.parseInt(map.get("excellentteacher").toString());
		int excellentcolldge = Integer.parseInt(map.get("excellentcolldge").toString());
		
		if(excellentperson>listpe.size())
		{
			ExcellentPersonMapper.addbatchExcellentPerson(listpe);
		}
		else
		{
			listpe.subList(0, excellentperson);
			ExcellentPersonMapper.addbatchExcellentPerson(listpe);
		}
		
		if(excellentcolldge>listco.size())
		{
			ExcellentCollegeMapper.addbatchExcellentCollege(listco);
		}
		else
		{
			listco.subList(0, excellentcolldge);
			ExcellentCollegeMapper.addbatchExcellentCollege(listco);
		}
		
		ExcellentPartmentMapperl.addbatchExcellentPartment(listde);
		
		
		int c = ExcellentTeacherMapper.countSumTeacher(competationid);
		if(c<excellentteacher && excellentteacher-c<listte.size())
		{
			listte.subList(0, excellentteacher-c);
			ExcellentTeacherMapper.addbatchExcellentTeacher(listte);
		}
		else if(c<excellentteacher && excellentteacher-c>listte.size())
		{
			ExcellentTeacherMapper.addbatchExcellentTeacher(listte);
		}
		return 1;
	}

	@Override
	public PageResult<Map<String,Object>> findRewardedList(PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		int count=CompetitionMapper.countfindCurrnetCompetition5();
		prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
		prr.setRowCount(count);
		List<Map<String,Object>> listcom = CompetitionMapper.findCurrnetCompetition5(pageModel.getPage(),pageModel.getPagesize());
		prr.setItems(listcom);
		return prr;
	}
	
	@Override
	public Map<String, Object> findfindRewardedListInfo(long id) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> listtheme = CompetitionMapper.findComWinList(id);
		Map<String,Object> mapz = new HashMap<String, Object>();
		if(listtheme!=null && listtheme.size()>0)
		{
			 mapz = case1(listtheme);
		}
		return mapz;
	}
	
	public Map<String,Object> case1(List<Map<String, Object>> list) throws Exception {
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listb = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listc = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listd = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> liste = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listf = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listg = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listh = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			String code = map.get("workType").toString();

			switch (code) {
			case "A":
				lista.add(map);
				break;
			case "B":
				listb.add(map);
				break;
			case "C":
				listc.add(map);
				break;
			case "D":
				listd.add(map);
				break;
			case "E":
				liste.add(map);
				break;
			case "F":
				listf.add(map);
				break;
			case "G":
				listg.add(map);
				break;
			default:
				listh.add(map);
				break;
			}

		}

		Map<String,Object> mapz = new HashMap<String, Object>();
		if(lista.size()>0)
		{
			mapz.put("平面类", lista);
		}
		if(listb.size()>0)
		{
			mapz.put("视频类影视广告", listb);
		}
		if(listc.size()>0)
		{
			mapz.put("视频类微电影广告", listc);
		}
		if(listd.size()>0)
		{
			mapz.put("动画类", listd);
		}
		if(liste.size()>0)
		{
			mapz.put("互动类", liste);
		}
		if(listf.size()>0)
		{
			mapz.put("广播类", listf);
		}
		if(listg.size()>0)
		{
			mapz.put("策划案类", listg);
		}
		if(listh.size()>0)
		{
			mapz.put("文案类", listh);
		}
		
		return mapz;

	}

	@Override
	public List<Map<String, Object>> findSchoolRewardedList(long enterpriseid,long competitionid, long divisionid) throws Exception {
		
		//1。查询对应赛区是否有评奖
		List<Map<String,Object>> list = CompetitionAprizeService.findCompetitionPrize(enterpriseid, competitionid, divisionid);
		if(list!=null && list.size()>0)
		{
			Map<String,Object> map = new HashMap<>();
			map.put("enterpriseid", enterpriseid);
			List<String> list11 = new ArrayList<String>();
			for (Map<String, Object> map11 : list) {
				list11.add(map11.get("id").toString());
			}
			map.put("list", list11); 
			return CompetitionAprizeMapper.findHUOjiang(map);
		}
		return  null;
			
	}



}
