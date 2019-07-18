package com.match.judges.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.judges.mapper.WorkJudgeItemMapper;
import com.match.judges.mapper.WorkJudgeMapper;
import com.match.judges.service.WorkJudgeService;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.ThemeWorkFileMapper;

@Service
public class WorkJudgeServiceImpl implements WorkJudgeService {

	@Autowired
	private  WorkJudgeMapper  WorkJudgeMapper;
	
	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	
	@Autowired
	private  ThemeWorkFileMapper ThemeWorkFileMapper;
	
	@Autowired
	private  WorkJudgeItemMapper  WorkJudgeItemMapper;
	
	@Override
	public PageResult<Map<String, Object>> findJudgeWork(long judgeID, int status,PageModel page) throws Exception {
		// TODO Auto-generated method stub
		PageResult<Map<String,Object>> pages = new PageResult<Map<String,Object>>();
		Map<String,Object>  map =  CompetitionMapper.findCurrnetCompetition3();
		if(map!=null)
		{
			//根据状态和竞赛ID去查询评委分配的作品
			int count=WorkJudgeMapper.countfindJudgeWork(judgeID, Long.parseLong(map.get("id").toString()), status);
			pages.setPages(Daomethod.countpages(count, page.getPagesize()));
			pages.setRowCount(count);
			
			List<Map<String,Object>>  list = WorkJudgeMapper.findJudgeWork(judgeID, Long.parseLong(map.get("id").toString()), status, page.getPage(), page.getPagesize());
			pages.setItems(list);
		}	
		else
		{
			pages.setErrmsg("暂无比赛");
		}
		return pages;
	}

	@Override
	public Map<String, Object> findWorkRuleInformation(long id) throws Exception {
		//查询作品信息以及命题详情
		Map<String,Object>  workmap = WorkJudgeMapper.findWorkInfo(id);
		//查询规则详情
		List<Map<String,Object>>  Rulelist = WorkJudgeMapper.fingRuleInfo(id);
		if(workmap!=null)
		{
			//查询未评判件数
			int allcount = WorkJudgeMapper.findGeShu(Long.parseLong(workmap.get("judgeID").toString()), Long.parseLong(workmap.get("competitionid").toString()), -1);
			//查询以评判件数
			int judgecount = WorkJudgeMapper.findGeShu(Long.parseLong(workmap.get("judgeID").toString()), Long.parseLong(workmap.get("competitionid").toString()), 2);
			workmap.put("othercount", allcount);
			workmap.put("judgecount", judgecount);
			workmap.put("allcount", allcount+judgecount);
			workmap.put("rule", Rulelist);
			if(Rulelist!=null && Rulelist.size()>0)
			{
				Map<String,Object>  map2 = Rulelist.get(0);
				workmap.put("type", map2.get("type"));
			}
			//查询评判分数
			List<Map<String,Object>> sclist = WorkJudgeItemMapper.findScoreByWorkJudgeId(Long.parseLong(workmap.get("id").toString()));
			if(sclist!=null && sclist.size()>0)
			{
				for (Map<String, Object> map : Rulelist) {
					for (Map<String, Object> map2 : sclist) {
						if(map.get("id").toString().equals(map2.get("ruleID").toString()))
						{
							map.put("shijiscore", map2.get("score").toString());
							break;
						}
					}
				}
			}
			
			//查询上传的作品
			workmap.put("file", ThemeWorkFileMapper.findMyThemeWorkFile(Long.parseLong(workmap.get("themeworkid").toString())));
		}
		return workmap;
	}

	@Override
	public Map<String, Object> findWorkRuleInformationManager(long id) throws Exception {
		// TODO Auto-generated method stub
		//查询作品信息以及命题详情
		Map<String,Object>  workmap = WorkJudgeMapper.findWorkInfo1(id);
		if(workmap!=null)
		{
			//查询上传的作品
			workmap.put("file", ThemeWorkFileMapper.findMyThemeWorkFile(Long.parseLong(workmap.get("themeworkid").toString())));
		}
		return workmap;
	}

}
