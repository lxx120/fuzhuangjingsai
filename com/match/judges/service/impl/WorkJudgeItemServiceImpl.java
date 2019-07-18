package com.match.judges.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.judges.mapper.WorkJudgeItemMapper;
import com.match.judges.mapper.WorkJudgeMapper;
import com.match.judges.model.WorkJudge;
import com.match.judges.model.WorkJudgeItem;
import com.match.judges.service.WorkJudgeItemService;

@Service
public class WorkJudgeItemServiceImpl implements WorkJudgeItemService {

	@Autowired
	private  WorkJudgeItemMapper WorkJudgeItemMapper;
	
	@Autowired
	private  WorkJudgeMapper  WorkJudgeMapper;
	
	@Override
	public int addWorkJudgeItem(WorkJudgeItem workJudgeItem,long status) throws Exception {
		
		List<Map<String,Object>>  list = workJudgeItem.getList();
		if(list!=null && list.size()>0)
		{
			//如果是投票制分数为1
			for (Map<String, Object> map : list) {
				map.put("mtime", UserChangLiang.mtime());
				map.put("type", status);
				map.put("workjudgeid", workJudgeItem.getWorkjudgeid());
			}
			WorkJudgeItemMapper.addBatchWorkJudgeItem(list);
			
			WorkJudge  workJudge = new WorkJudge();
			workJudge.setJudgetime(UserChangLiang.mtime());
			workJudge.setId(workJudgeItem.getWorkjudgeid());
			workJudge.setJudged(2);  //已经评分
			
			Double juweight = WorkJudgeMapper.findWeight(workJudgeItem.getWorkjudgeid());
			//修改评委作品表
			//评分
			if(1==status)
			{
				Double ss = 0.00;
				for (Map<String, Object> map : list) {
					ss = ss+Double.parseDouble(map.get("weight").toString())*Double.parseDouble(map.get("score").toString());
				}
				workJudge.setScore(ss*juweight);
			}
			else if(2==status)
			{
				Map<String,Object> map = list.get(0);
				//投票了
				Double score = Double.valueOf(map.get("score").toString());
				int i = new Double(score).intValue();
				if(1==i)
				{
					workJudge.setIsvote(1);
					workJudge.setScore(1.00*juweight);
				}
				else if(0==i)
				{
					workJudge.setIsvote(2);
					workJudge.setScore(0.00);
				}
			}
			int i = WorkJudgeMapper.updateByPrimaryKeySelective(workJudge);
			if(i>0)
			{
				return 1;
			}
			return -1;
		}
		return 2;//打分项为空不能添加
	}

}
