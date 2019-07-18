package com.match.controllers.Quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.match.judges.service.AssessRoundsService;
import com.match.proposition.service.ThemeService;

@Controller
public class AssessRoundsQuartzController {

	@Autowired
	private  AssessRoundsService  AssessRoundsService;
	
	@Autowired
	private  ThemeService  ThemeService;
	
	@Scheduled(cron = "0 58 16 * * ? ")
	public void updateQuartzAssessRounds()  throws  Exception
	{
		AssessRoundsService.updateQuartzAssessRounds();
	}
	
	@Scheduled(cron = "0 3 18 * * ? ")
	public void batchUpdateTheme()  throws  Exception
	{
		ThemeService.updateQuartzBatchTheme();
	}
}
