package com.match.weixin.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Refresh_accesstoken_job implements Job{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		WechatUtils.refreshAccessToken();
	}
}
