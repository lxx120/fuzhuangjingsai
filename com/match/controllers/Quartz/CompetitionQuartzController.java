package com.match.controllers.Quartz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.match.proposition.service.CompetitionService;

@Controller
public class CompetitionQuartzController {

	@Autowired
	private CompetitionService  CompetitionService;
	//
	public  void  findAll()
	{
		try {
			List<Map<String, Object>> lsit = CompetitionService.fidnAllCompetition();
			System.out.println(lsit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 每天十二点修改比赛状态
	 */
	public  void  updateCompetitionStatus()  throws  Exception
	{
		boolean flag = CompetitionService.updateCompetitionStatus();
		if(flag)
		{
			System.out.println("OK");
		}
		else
		{
			System.out.println("NO");
		}
	}
	
	
}
