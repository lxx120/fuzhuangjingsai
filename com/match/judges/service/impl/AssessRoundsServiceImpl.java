package com.match.judges.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.match.baciss.cl.UserChangLiang;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.judges.mapper.AssessDaiWorkMapper;
import com.match.judges.mapper.AssessRoundsMapper;
import com.match.judges.mapper.CompetitionAprizeMapper;
import com.match.judges.mapper.WinningPromotionMapper;
import com.match.judges.mapper.WorkJudgeMapper;
import com.match.judges.mapper.WorksAccessResultMapper;
import com.match.judges.model.AssessDaiWork;
import com.match.judges.model.AssessRounds;
import com.match.judges.model.WinningPromotion;
import com.match.judges.model.WorksAccessResult;
import com.match.judges.service.AssessRoundsService;
import com.match.proposition.cl.ComChangLiang;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.ThemeWorkMapper;
import com.match.proposition.model.ThemeWork;

@Service
public class AssessRoundsServiceImpl implements AssessRoundsService {

	@Autowired
	private AssessRoundsMapper AssessRoundsMapper;

	@Autowired
	private CompetitionMapper CompetitionMapper;

	@Autowired
	private WorkJudgeMapper WorkJudgeMapper;

	@Autowired
	private WorksAccessResultMapper WorksAccessResultMapper;

	@Autowired
	private WinningPromotionMapper WinningPromotionMapper;

	@Autowired
	private AssessDaiWorkMapper AssessDaiWorkMapper;

	@Autowired
	private CompetitionAprizeMapper CompetitionAprizeMapper;

	@Autowired
	private ThemeWorkMapper ThemeWorkMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, RuntimeException.class })
	public int addAssessRounds(AssessRounds assessRounds, long divisionid) throws Exception {
		// TODO Auto-generated method stub
		
		assessRounds.setIschoose(-1);
		assessRounds.setMtime(UserChangLiang.mtime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = sdf.parse(sdf.format(new Date()));
		// 查询当前的竞赛
		Map<String, Object> map = CompetitionMapper.findCurrentCompetition(divisionid);
		if (map != null && map.size() > 0) {

//			Date commitetime = sdf.parse(map.get("commitetime").toString());
//			if (s1.compareTo(commitetime) < 0) {
//				return 5;
//			}
			if (map.get("arid") != null) {
				Date reviewetime = sdf.parse(map.get("reviewetime").toString());
				if (reviewetime.compareTo(s1) > 0) {
					return -4;
				}
			}
			assessRounds.setCompetitionid((long) Integer.parseInt(map.get("id").toString()));
			// 添加赛区id
			if (assessRounds.getDivisionid() != 0) {
				assessRounds.setAhid((long) 2);
			} else {
				assessRounds.setAhid((long) 1);
			}
			// 添加第几轮次
			if (map.get("maxlevel") != null) {
				assessRounds.setPre((long) Integer.parseInt(map.get("arid").toString()));
				assessRounds.setLevel(Integer.parseInt(map.get("maxlevel").toString()) + 1);
			} else {
				assessRounds.setPre((long) 0);
				assessRounds.setLevel(1);
			}
			// 添加是判断轮次的状态
			Date s = sdf.parse(assessRounds.getReviewstime());

			int tiems = s.compareTo(s1);
			if (tiems > 0) {
				// 如果设置的时间大于现在时间，说明轮次未开始
				assessRounds.setStatus(ComChangLiang.ready);
			} else {
				return -3;
			}

			int i = AssessRoundsMapper.insertSelective(assessRounds);

			// 添加轮次待分配的作品
			// 分赛区不是第一轮或者总赛不是第一轮
			if ((assessRounds.getLevel() != 1) || (assessRounds.getDivisionid() == 0 && assessRounds.getLevel() != 1)) {
				if (!assessRounds.getMark().equals(map.get("mark").toString())) {
					List<Map<String, Object>> liss = WinningPromotionMapper.findWorkList(map.get("mark").toString(),
							divisionid, (long) Integer.parseInt(map.get("id").toString()));
					if (liss != null && liss.size() > 0) {
						List<AssessDaiWork> list = new ArrayList<AssessDaiWork>();
						for (Map<String, Object> map1 : liss) {
							AssessDaiWork assessDaiWork = new AssessDaiWork();
							assessDaiWork.setWorkid(Long.parseLong(map1.get("workid").toString()));
							assessDaiWork.setMtime(UserChangLiang.mtime());
							assessDaiWork.setArid(assessRounds.getId());
							assessDaiWork.setDivisionid(assessRounds.getDivisionid());
							assessDaiWork.setCompetitionid((long) Integer.parseInt(map.get("id").toString()));
							list.add(assessDaiWork);
						}
						AssessDaiWorkMapper.addBatchAssessDaiWork(list);
					}
				}
			}
			// 总赛第一轮的时候去查询晋级的作品
			else if (assessRounds.getDivisionid() == 0 && assessRounds.getLevel() == 1) {
				List<Map<String, Object>> liss = WinningPromotionMapper
						.findWorkList1((long) Integer.parseInt(map.get("id").toString()));
				if (liss != null && liss.size() > 0) {
					List<AssessDaiWork> list = new ArrayList<AssessDaiWork>();
					for (Map<String, Object> map1 : liss) {
						AssessDaiWork assessDaiWork = new AssessDaiWork();
						assessDaiWork.setWorkid(Long.parseLong(map1.get("workid").toString()));
						assessDaiWork.setMtime(UserChangLiang.mtime());
						assessDaiWork.setArid(assessRounds.getId());
						assessDaiWork.setDivisionid(assessRounds.getDivisionid());
						assessDaiWork.setCompetitionid((long) Integer.parseInt(map.get("id").toString()));
						list.add(assessDaiWork);
					}
					AssessDaiWorkMapper.addBatchAssessDaiWork(list);
				}
			}

			if (i > 0) {
				return 1;
			}
			return -1;
		}
		return -2;
	}

	@Override
	public int updateAssessRounds(AssessRounds assessRounds, long divisionid) throws Exception {
		// TODO Auto-generated method stub

		Map<String, Object> map = CompetitionMapper.findCurrentCompetition1(divisionid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = sdf.parse(sdf.format(new Date()));
		if (map != null) {
			Date reviewetime = sdf.parse(map.get("reviewetime").toString());
			Date reviewstime = sdf.parse(map.get("reviewstime").toString());
			if (s1.compareTo(reviewstime) >= 0 && s1.compareTo(reviewetime) < 0) {
				return 2; // 轮次正在进行中，无法修改
			}
			if (s1.compareTo(reviewetime) >= 0) {
				return 3; // 轮次进行完毕，无法修改
			}
			if (s1.compareTo(reviewstime) < 0) {
				int i = AssessRoundsMapper.updateByPrimaryKeySelective(assessRounds);
				if (i > 0) {
					return 1;
				}
				return -1;
			}
		}
		return 4; // 暂无进行的比赛或者轮次

	}

	@Override
	public int deleteAssessRounds(long id, long divisionid) throws Exception {
		// TODO Auto-generated method stub

		Map<String, Object> map = CompetitionMapper.findCurrentCompetition1(divisionid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = sdf.parse(sdf.format(new Date()));
		if (map != null) {
			Date reviewetime = sdf.parse(map.get("reviewetime").toString());
			Date reviewstime = sdf.parse(map.get("reviewstime").toString());
			if (s1.compareTo(reviewstime) >= 0 && s1.compareTo(reviewetime) < 0) {
				return 2; // 轮次正在进行中，无法修改
			}
			if (s1.compareTo(reviewetime) >= 0) {
				return 3; // 轮次进行完毕，无法修改
			}
			if (s1.compareTo(reviewstime) < 0) {
				int i = AssessRoundsMapper.deleteByPrimaryKey(id);
				if (i > 0) {
					return 1;
				}
				return -1;
			}
		}
		return 4; // 暂无进行的比赛或者轮次
	}

	@Override
	public List<Map<String, Object>> findAssessRoundsPage(AssessRounds assessRounds, long divisionid) throws Exception {

		// 查询当前的竞赛
		Map<String, Object> map = CompetitionMapper.findCurrnetCompetition3();
		if (map != null) {
			assessRounds.setCompetitionid((long) Integer.parseInt(map.get("id").toString()));
			assessRounds.setDivisionid(divisionid);
			// 查询轮次
			List<Map<String, Object>> list = AssessRoundsMapper.findAssessRounds(assessRounds);
			if (list != null && list.size() > 0) {
				for (Map<String, Object> map1 : list) {
					if (assessRounds.getDivisionid() == 0) {
						map1.put("diname", "总赛");
					}
					map1.put("arname", "第" + map1.get("level").toString() + "轮");
				}
			}
			return list;
		}
		return null;
	}

	@Override
	public void updateQuartzAssessRounds() throws Exception {

		// 查询所有未开始和正在进行的轮次
		List<Map<String, Object>> list = AssessRoundsMapper.findAllAssessRounds();
		if (list != null && list.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date s1 = sdf.parse(sdf.format(new Date()));
			List<AssessRounds> listass = new ArrayList<AssessRounds>();
			for (Map<String, Object> map : list) {
				// 如果轮次正在进行
				if (1 == Integer.parseInt(map.get("status").toString())) {
					Date reviewetime = sdf.parse(map.get("reviewetime").toString());
					if (s1.compareTo(reviewetime) >= 0) {
						AssessRounds assessRounds = new AssessRounds();
						assessRounds.setId(Long.parseLong(map.get("id").toString()));
						assessRounds.setStatus(ComChangLiang.close);
//						assessRounds.setMtime(UserChangLiang.mtime());
						listass.add(assessRounds);
					}
				} else if (0 == Integer.parseInt(map.get("status").toString())) {
					Date reviewetime = sdf.parse(map.get("reviewstime").toString());
					if (s1.compareTo(reviewetime) >= 0) {
						AssessRounds assessRounds = new AssessRounds();
						assessRounds.setId(Long.parseLong(map.get("id").toString()));
						assessRounds.setStatus(ComChangLiang.start);
//						assessRounds.setMtime(UserChangLiang.mtime());
						listass.add(assessRounds);
					}
				}
			}

			// 进行批量修改
			AssessRoundsMapper.updatebatch(listass);
		}

	}

	@Override
	public int addDistribution() throws Exception {

		// 1.先去查询轮次到期的，并且查询相对应的轮次规则
//		List<Map<String, Object>> overlist = AssessRoundsMapper.findOverAssessRounds();
//		if (overlist != null && overlist.size() > 0) {
//			for (Map<String, Object> map : overlist) {
//				List<WorksAccessResult> WorksAccessResultlist = new ArrayList<WorksAccessResult>();
//				// 查询轮次对应的作品
//				List<Map<String, Object>> worklist = AssessRoundsMapper
//						.findWorkidByArid(Long.parseLong(map.get("id").toString()));
//				// 把作品的总分什么的拿出来，放在list里面
//				for (Map<String, Object> map2 : worklist) {
//					{
//						WorksAccessResult worksAccessResult = new WorksAccessResult();
//						Double sumscore = WorkJudgeMapper
//								.findWorkSumScore(Long.parseLong(map2.get("worksID").toString()),Long.parseLong(infomap.get("id").toString()));
//						worksAccessResult.setWorkid(Long.parseLong(map2.get("worksID").toString()));
//						worksAccessResult.setMtime(UserChangLiang.mtime());
//						worksAccessResult.setCompetitionid(Long.parseLong(map.get("competitionid").toString()));
//						worksAccessResult.setDivisionid(Long.parseLong(map.get("divisionid").toString()));
//						worksAccessResult.setArid(Long.parseLong(map.get("id").toString()));
//						if (1 == Integer.parseInt(map.get("catype").toString())) {
//							worksAccessResult.setWartype(1);
//							worksAccessResult.setTotalscore(sumscore);
//							worksAccessResult.setTotalticket(0.00);
//						} else {
//							worksAccessResult.setWartype(2);
//							worksAccessResult.setTotalticket(sumscore);
//							worksAccessResult.setTotalscore(0.00);
//						}
//						WorksAccessResultlist.add(worksAccessResult);
//					}
//				}
//
//				// 对轮次所对的作品进行排序(重写compare方法)
//				Collections.sort(WorksAccessResultlist, new Comparator<WorksAccessResult>() {
//
//					@Override
//					public int compare(WorksAccessResult o1, WorksAccessResult o2) {
//						// TODO Auto-generated method stub
//						Double dff = o2.getTotalscore() - o1.getTotalscore();
//						if (dff > 0) {
//							return 1;
//						} else if (dff < 0) {
//							return -1;
//						}
//						return 0;
//					}
//				});
//
//				int ranking = 0;
//				for (WorksAccessResult ww : WorksAccessResultlist) {
//					ww.setRanking(ranking + 1);
//					ranking = ranking + 1;
//				}
//
//				// 批量添加到轮次作品总评结果
//				int s1 = WorksAccessResultMapper.batchAddWorksAccessResult(WorksAccessResultlist);
//			}
//		}
		return 1;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, RuntimeException.class })
	public PageResult<Map<String, Object>> addDistributionByArid(String code1,long arid, PageModel pageModel) throws Exception {

		int type = 0;
		PageResult<Map<String, Object>> page = new PageResult<Map<String, Object>>();
		// 根据轮次的信息
		Map<String, Object> infomap = AssessRoundsMapper.findOverAssessRoundsByArid(arid);
		int pagecode = 0;
		// 查询伦次配置信息
		List<Map<String, Object>> inforlist = CompetitionAprizeMapper.findCompetitionAprizeMapperMap(arid);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = sdf.parse(sdf.format(new Date()));
		Date reviewetime = sdf.parse(infomap.get("reviewetime").toString());
		if (s1.compareTo(reviewetime) < 0) {
			page.setErrmsg("轮次未结束，无法查看");
			page.setItems(null);
		} else {
			if (0 == Integer.parseInt(infomap.get("generalcomment").toString())) {

				// 查询轮次对应的作品
				List<Map<String, Object>> worklist = AssessRoundsMapper
						.findWorkidByArid(Long.parseLong(infomap.get("id").toString()));

				//按照类型分类
				List<List<Map<String,Object>>> listz = case1(worklist);
				for (List<Map<String, Object>> listq : listz) {
					
					List<WorksAccessResult> WorksAccessResultlist = new ArrayList<WorksAccessResult>();
					List<WinningPromotion> winningPromotions = new ArrayList<WinningPromotion>();
					List<ThemeWork> themeworklist = new ArrayList<ThemeWork>();

					String code="";
					// 把作品的总分什么的拿出来，放在list里面
					for (Map<String, Object> map2 : listq) {
						{
							code = map2.get("workType").toString();
							WorksAccessResult worksAccessResult = new WorksAccessResult();
							Double sumscore = WorkJudgeMapper.findWorkSumScore(
									Long.parseLong(map2.get("worksID").toString()),
									Long.parseLong(infomap.get("id").toString()));
							worksAccessResult.setWorkid(Long.parseLong(map2.get("worksID").toString()));
							worksAccessResult.setMtime(UserChangLiang.mtime());
							worksAccessResult.setCompetitionid(Long.parseLong(infomap.get("competitionid").toString()));
							worksAccessResult.setDivisionid(Long.parseLong(infomap.get("divisionid").toString()));
							worksAccessResult.setArid(Long.parseLong(infomap.get("id").toString()));
							worksAccessResult.setWtypecode(code);
							type = Integer.parseInt(infomap.get("type").toString());
							if (1 == Integer.parseInt(infomap.get("type").toString())) {
								worksAccessResult.setWartype(1);
								worksAccessResult.setTotalscore(sumscore);
								worksAccessResult.setTotalticket(0.00);
							} else {
								worksAccessResult.setWartype(2);
								worksAccessResult.setTotalticket(sumscore);
								worksAccessResult.setTotalscore(0.00);
							}
							WorksAccessResultlist.add(worksAccessResult);
						}
					}
					// 对轮次所对的作品进行排序(重写compare方法)
					if(type==1)
					{
						Collections.sort(WorksAccessResultlist, new Comparator<WorksAccessResult>() {

							@Override
							public int compare(WorksAccessResult o1, WorksAccessResult o2) {
								// TODO Auto-generated method stub
								Double dff = o2.getTotalscore() - o1.getTotalscore();
								if (dff > 0) {
									return 1;
								} else if (dff < 0) {
									return -1;
								}
								return 0;
							}
						});
					}
					else
					{
						Collections.sort(WorksAccessResultlist, new Comparator<WorksAccessResult>() {

							@Override
							public int compare(WorksAccessResult o1, WorksAccessResult o2) {
								// TODO Auto-generated method stub
								Double dff = o2.getTotalticket() - o1.getTotalticket();
								if (dff > 0) {
									return 1;
								} else if (dff < 0) {
									return -1;
								}
								return 0;
							}
						});
					}
					
					

					int ranking = 0;
					for (WorksAccessResult ww : WorksAccessResultlist) {
						ww.setRanking(ranking + 1);
						ranking = ranking + 1;
					}

					// 批量添加到轮次作品总评结果
					int z = WorksAccessResultMapper.batchAddWorksAccessResult(WorksAccessResultlist);
					

					// 晋级或者向上一级的情况
					if (inforlist != null && inforlist.size() == 1) {
						Map<String, Object> infomap1 = inforlist.get(0);
						int maxPercent = Integer.parseInt(infomap1.get("maxPercent").toString());

						// 判断是否有分数一样的情况
						if (WorksAccessResultlist.size() > maxPercent) {
							// 获取截止位置的人的分数
							WorksAccessResult score = WorksAccessResultlist.get(maxPercent - 1);
							int typez = score.getWartype();
							List<Integer> lista = new ArrayList<>();
							//投票
							if(1==typez)
							{
								BigDecimal data1 = new BigDecimal(score.getTotalscore()); 
								// 把分数相同的拿出来
								for (int i = 0; i < WorksAccessResultlist.size(); i++) {
									WorksAccessResult score1 = WorksAccessResultlist.get(i);
									BigDecimal data2 = new BigDecimal(score1.getTotalscore()); 
									int falg = data1.compareTo(data2); 
									if (i != (maxPercent - 1) && falg==0) {
										lista.add(i);
									}
								}
							}
							else
							{
								BigDecimal data1 = new BigDecimal(score.getTotalticket()); 
								// 把分数相同的拿出来
								for (int i = 0; i < WorksAccessResultlist.size(); i++) {
									WorksAccessResult score1 = WorksAccessResultlist.get(i);
									BigDecimal data2 = new BigDecimal(score1.getTotalticket()); 
									int falg = data1.compareTo(data2); 
									if (i != (maxPercent - 1) && falg==0) {
										lista.add(i);
									}
								}
							}
							if (lista.size() > 0) {
								Collections.sort(lista);
								int ss = (int) lista.get(0);
								for (int i = 0; i < ss - 1; i++) {
									WorksAccessResult ww = WorksAccessResultlist.get(i);
									WinningPromotion winningPromotion = new WinningPromotion();
									winningPromotion.setArid(ww.getArid());
									winningPromotion.setCompetitionid(ww.getCompetitionid());
									winningPromotion.setDivisionid(ww.getDivisionid());
									winningPromotion.setRanking(ww.getRanking());
									winningPromotion.setWptype(Integer.parseInt(infomap1.get("catype").toString()));
									winningPromotion
											.setCompetitionAprizeid(Long.parseLong(infomap1.get("id").toString()));
									winningPromotion.setPtypecode(code);
									winningPromotion.setMark(infomap.get("mark").toString());
									winningPromotion.setMtime(UserChangLiang.mtime());
									winningPromotion.setWorkid(ww.getWorkid());
									winningPromotions.add(winningPromotion);

									ThemeWork themeWork = new ThemeWork();
									themeWork.setId(ww.getWorkid());
									themeWork.setFinalaprizeid(Long.parseLong(infomap1.get("id").toString()));
									themeworklist.add(themeWork);
								}
								ThemeWorkMapper.updateBatch(themeworklist);
								WinningPromotionMapper.addBatchWin(winningPromotions);
								page.setErrmsg("有分数相同的情况，请修改晋级人数并且重新开启轮次");
								pagecode = 2;
								page.setCode(pagecode);
							} else {
								for (int i = 0; i < maxPercent; i++) {
									WorksAccessResult ww = WorksAccessResultlist.get(i);
									WinningPromotion winningPromotion = new WinningPromotion();
									winningPromotion.setArid(ww.getArid());
									winningPromotion.setCompetitionid(ww.getCompetitionid());
									winningPromotion.setDivisionid(ww.getDivisionid());
									winningPromotion.setRanking(ww.getRanking());
									winningPromotion.setPtypecode(code);
									winningPromotion.setWptype(Integer.parseInt(infomap1.get("catype").toString()));
									winningPromotion
											.setCompetitionAprizeid(Long.parseLong(infomap1.get("id").toString()));
									winningPromotion.setMark(infomap.get("mark").toString());
									winningPromotion.setMtime(UserChangLiang.mtime());
									winningPromotion.setWorkid(ww.getWorkid());
									winningPromotions.add(winningPromotion);

									ThemeWork themeWork = new ThemeWork();
									themeWork.setId(ww.getWorkid());
									themeWork.setFinalaprizeid(Long.parseLong(infomap1.get("id").toString()));
									themeworklist.add(themeWork);
								}
								ThemeWorkMapper.updateBatch(themeworklist);

								WinningPromotionMapper.addBatchWin(winningPromotions);
							}

						} else {
							// 添加到轮次获奖或晋级作品表中
							for (WorksAccessResult ww : WorksAccessResultlist) {
								WinningPromotion winningPromotion = new WinningPromotion();
								winningPromotion.setArid(ww.getArid());
								winningPromotion.setCompetitionid(ww.getCompetitionid());
								winningPromotion.setDivisionid(ww.getDivisionid());
								winningPromotion.setRanking(ww.getRanking());
								winningPromotion.setWptype(Integer.parseInt(infomap1.get("catype").toString()));
								winningPromotion.setCompetitionAprizeid(Long.parseLong(infomap1.get("id").toString()));
								winningPromotion.setMark(infomap.get("mark").toString());
								winningPromotion.setMtime(UserChangLiang.mtime());
								winningPromotion.setPtypecode(code);
								winningPromotion.setWorkid(ww.getWorkid());
								winningPromotions.add(winningPromotion);

								ThemeWork themeWork = new ThemeWork();
								themeWork.setId(ww.getWorkid());
								themeWork.setFinalaprizeid(Long.parseLong(infomap1.get("id").toString()));
								themeworklist.add(themeWork);

							}
							ThemeWorkMapper.updateBatch(themeworklist);
							WinningPromotionMapper.addBatchWin(winningPromotions);
						}
					}
					// 评奖的时候
					else {
						int  zz = 0;
						int zz1 = 0;
						for (Map<String, Object> map : inforlist) {
							List<WinningPromotion> winningPromotions1 = new ArrayList<WinningPromotion>();
							List<ThemeWork> themeworklist1 = new ArrayList<ThemeWork>();
							int maxPercent = Integer.parseInt(map.get("maxPercent").toString());
							zz1 = maxPercent;
							for (int i = zz; i < maxPercent+zz; i++) {
								if(WorksAccessResultlist.size()<=i)
								{
									break;
								}
								WorksAccessResult ww = WorksAccessResultlist.get(i);
								WinningPromotion winningPromotion = new WinningPromotion();
								winningPromotion.setArid(ww.getArid());
								winningPromotion.setCompetitionid(ww.getCompetitionid());
								winningPromotion.setDivisionid(ww.getDivisionid());
								winningPromotion.setRanking(ww.getRanking());
								winningPromotion.setWptype(Integer.parseInt(map.get("catype").toString()));
								winningPromotion.setCompetitionAprizeid(Long.parseLong(map.get("id").toString()));
								winningPromotion.setMark(infomap.get("mark").toString());
								winningPromotion.setMtime(UserChangLiang.mtime());
								winningPromotion.setPtypecode(code);
								winningPromotion.setWorkid(ww.getWorkid());
								winningPromotions1.add(winningPromotion);

								ThemeWork themeWork = new ThemeWork();
								themeWork.setId(ww.getWorkid());
 								themeWork.setFinalaprizeid(Long.parseLong(map.get("id").toString()));
 								themeworklist1.add(themeWork);
							}
							if(themeworklist1.size()>0)
							{
								ThemeWorkMapper.updateBatch(themeworklist1);
							}
							if(winningPromotions1.size()>0)
							{
								WinningPromotionMapper.addBatchWin(winningPromotions1);
							}
							zz = zz1+zz;
						}
					}

				}
				
				// 修改轮次状态
				AssessRounds assessRounds = new AssessRounds();
				assessRounds.setId(arid);
				assessRounds.setGeneralcomment(1);
				AssessRoundsMapper.updateByPrimaryKeySelective(assessRounds);
			}

			int count = WorksAccessResultMapper
					.countfindWorksAccessResult(code1,Long.parseLong(infomap.get("id").toString()));
			page.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			page.setRowCount(count);
			List<Map<String, Object>> list = WorksAccessResultMapper.findWorksAccessResult(
					code1,Long.parseLong(infomap.get("id").toString()), pageModel.getPage(), pageModel.getPagesize());
			page.setItems(list);
			page.setCode(pagecode);
			if(0==pagecode)
			{
				page.setErrmsg("查询成功");
			}
		}
		return page;
	}

	@Override
	public Map<String, Object> findAssessRoundsById(long id) throws Exception {
		// TODO Auto-generated method stub
		return AssessRoundsMapper.findAssessRoundsById(id);
	}

	public List<List<Map<String,Object>>> case1(List<Map<String, Object>> list) throws Exception {
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

		List<List<Map<String,Object>>> listz = new ArrayList();
		if(lista.size()>0)
		{
			listz.add(lista);
		}
		if(listb.size()>0)
		{
			listz.add(listb);
		}
		if(listc.size()>0)
		{
			listz.add(listc);
		}
		if(listd.size()>0)
		{
			listz.add(listd);
		}
		if(liste.size()>0)
		{
			listz.add(liste);
		}
		if(listf.size()>0)
		{
			listz.add(listf);
		}
		if(listg.size()>0)
		{
			listz.add(listg);
		}
		if(listh.size()>0)
		{
			listz.add(listh);
		}
		
		return listz;

	}

}
