package com.match.judges.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.judges.mapper.JudgeRuleMapper;
import com.match.judges.model.JudgeRule;
import com.match.judges.service.JudgeRuleService;

@Service
public class JudgeRuleServiceImpl implements JudgeRuleService {

	@Resource
	private  JudgeRuleMapper  judgeRuleMapper;
	
	@Override
	public boolean addJudgeRule(List<Map<String,Object>> list) throws Exception {
		// TODO Auto-generated method stub
		for (Map<String, Object> map : list) {
			map.put("mtime", UserChangLiang.mtime());
		}
		int i = judgeRuleMapper.insetjudgeRuleList(list);
		if(i>0)
		{
			return  true;
		}
		return false;
	}

	@Override
	public boolean deleteJudgeRule(long id) throws Exception {
		// TODO Auto-generated method stub
		int i = judgeRuleMapper.deleteByPrimaryKey(id);
		if(i>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean updateJudgeRule(JudgeRule judgeRule) throws Exception {
		// TODO Auto-generated method stub
		int i = judgeRuleMapper.updateByPrimaryKeySelective(judgeRule);
		if(i>0)
		{
			return  true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> findJudgeRuleList(long arid) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  list =  judgeRuleMapper.findjudgeRuleList(arid);
		return list;
	}

	@Override
	public Map<String, Object> findJudgeRuleInfo(long id) throws Exception {
		// TODO Auto-generated method stub
		return judgeRuleMapper.findjudgeRuleInfo(id);
	}

}
