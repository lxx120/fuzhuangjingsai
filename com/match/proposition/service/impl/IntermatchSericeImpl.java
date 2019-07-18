package com.match.proposition.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.IntermatchMapper;
import com.match.proposition.mapper.IntermatchpersonMapper;
import com.match.proposition.model.Intermatch;
import com.match.proposition.model.Intermatchperson;
import com.match.proposition.service.IntermatchSerice;

@Service
public class IntermatchSericeImpl implements IntermatchSerice {

	@Autowired
	private  IntermatchMapper  IntermatchMapper;
	
	@Autowired
	private  CompetitionMapper  CompetitionMapper;
	
	@Autowired
	private  IntermatchpersonMapper  IntermatchpersonMapper;
	
	@Override
	public int addIntermatch(Intermatch intermatch) throws Exception {

		//查询是否在比赛期间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = sdf.parse(sdf.format(new Date()));
		Map<String,Object>  mapcom = CompetitionMapper.findCurrnetCompetition3();
		intermatch.setCompetitionid(Integer.parseInt(mapcom.get("id").toString()));
		if(mapcom!=null)
		{
			Date intermatchstime = sdf.parse(mapcom.get("intermatchstime").toString());
			Date intermatchetime = sdf.parse(mapcom.get("intermatchetime").toString());
			if(s1.compareTo(intermatchstime)<0)
			{
				return 3;  //网络复活赛还未开始
			}
			if(s1.compareTo(intermatchetime)>0)
			{
				return 4; //网络复活赛已经结束
			}
		}
		else
		{
			return 5; //暂无比赛
		}
		//查询作品是否获过奖
		Map<String,Object> mapp = IntermatchMapper.findIsPrize(intermatch.getThemeworkid());
		if(mapp!=null && 2==Integer.parseInt(mapp.get("catype").toString()))
		{
			return 2;  //已经获过奖，不能参与网络比赛
		}
		
		//查询是否提交过
		Map<String,Object>  mapexist = IntermatchMapper.findisExist(intermatch.getThemeworkid(), intermatch.getCompetitionid());
		if(mapexist!=null)
		{
			return 6;//此作品已参加网络比赛
		}
		
		//添加
		intermatch.setMtime(UserChangLiang.mtime());
		intermatch.setCompetitionid(Integer.parseInt(mapcom.get("id").toString()));
		int i = IntermatchMapper.addIntermatch(intermatch);
		if(i>0)
		{
			return 1;
		}
		
		return -1;
	}

	@Override
	public int updateIntermatch(Intermatchperson intermatchperson) throws Exception {
		
		//查询是否投过票
		Map<String,Object> emap = IntermatchpersonMapper.findExist(intermatchperson.getPersonid(), intermatchperson.getCompetitionid());
		if(emap!=null)
		{
			return 2;  //您已投过票
		}
		//投票
		intermatchperson.setMtime(UserChangLiang.mtime());
		int i = IntermatchpersonMapper.addIntermatchperson(intermatchperson);
		//改分数
		Intermatch intermatch = new Intermatch();
		intermatch.setId(intermatchperson.getIntermatchid());
		int vote = IntermatchMapper.findVote(intermatchperson.getIntermatchid());
		intermatch.setVote(vote+1);
		int k = IntermatchMapper.updateIntermatch(intermatch);
		if(i>0 && k>0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public PageResult<Map<String, Object>> findIntermatch(String code, long enterpriseid,
			PageModel pageModel) throws Exception {
		Map<String,Object> map = CompetitionMapper.findCurrnetCompetition3();
		long competitionid=0;
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		if (map!=null) {
			competitionid = Integer.parseInt(map.get("id").toString());
			int count=IntermatchMapper.countfindfindIntermatch(competitionid, code, enterpriseid);
			prr.setPages(Daomethod.countpages(count, pageModel.getPagesize()));
			prr.setRowCount(count);
			List<Map<String,Object>> ml = IntermatchMapper.findfindIntermatch(competitionid, code, enterpriseid, pageModel.getPage(), pageModel.getPagesize());
			prr.setItems(ml);
			return prr;
		}
		return prr;
	}

}
