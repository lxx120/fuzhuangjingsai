package com.match.proposition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.baciss.cl.UserChangLiang;
import com.match.proposition.cl.CheatReportChangLiang;
import com.match.proposition.mapper.CheatReportMapper;
import com.match.proposition.mapper.ThemeWorkMapper;
import com.match.proposition.model.CheatReport;
import com.match.proposition.model.ThemeWork;
import com.match.proposition.service.CheatReportService;

@Service
public class CheatReportServiceImpl implements CheatReportService {

	@Autowired
	private  CheatReportMapper  CheatReportMapper;
	
	@Autowired
	private  ThemeWorkMapper  ThemeWorkMapper;
	
	@Override
	public int addCheatReport(CheatReport cheatReport) throws Exception {
		// TODO Auto-generated method stub
		cheatReport.setMtime(UserChangLiang.mtime());
		cheatReport.setSyscheck(CheatReportChangLiang.nosyscheck);
		cheatReport.setChecked(false);//是否被检测
		int i = CheatReportMapper.insertSelective(cheatReport);
		if(i>0)
		{
			ThemeWork themeWork = new ThemeWork();
			themeWork.setId(cheatReport.getThemeworkid());
			themeWork.setCheat(cheatReport.getCrtype());
			themeWork.setMtime(UserChangLiang.mtime());
			return ThemeWorkMapper.updateByPrimaryKeySelective(themeWork);
		}
		return i;
	}

}
