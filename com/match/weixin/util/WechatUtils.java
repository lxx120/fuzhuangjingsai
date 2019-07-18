package com.match.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.match.common.JSONobj;
import com.match.quartz.Quartzjob;


public class WechatUtils {
	private static Logger log=LoggerFactory.getLogger(WechatUtils.class);
	public static String appid=null;
	public static String secret=null;
	public static String token=null;
	private static String access_token=null;

	static{
		Properties p=new Properties();
		InputStream in=WechatUtils.class.getResourceAsStream("/wechat.properties");
		try{
			p.load(in);
			appid=p.getProperty("appid");
			secret=p.getProperty("secret");
			token=p.getProperty("token");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String access_token(){
		if(access_token==null){
			access_token=getAccessToken();
			// init refresh accesstoken job
			String timepattern="0 0 0/2 * * ?";
			try{
				@SuppressWarnings("rawtypes")
				TriggerBuilder tb=TriggerBuilder.newTrigger();
				tb.withIdentity("accesstokentrigger", "accesstokentriggergroup").withSchedule(CronScheduleBuilder.cronSchedule(timepattern));
				Trigger tr=tb.build();
				Quartzjob.runJob("tokentask", Refresh_accesstoken_job.class, tr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return access_token;
	}
	
	
	public static void refreshAccessToken(){
		access_token=getAccessToken();
	}
	
	public static String getAccessToken(){
		String appid=WechatUtils.appid;
		String secret=WechatUtils.secret;
		StringBuffer sb=new StringBuffer();
		sb.append("https://api.weixin.qq.com/cgi-bin/token");
		sb.append("?grant_type=client_credential");
		sb.append("&appid="+appid);
		sb.append("&secret="+secret);
		String res=Wxhtmethod.ssl_get(sb.toString());
		if(res==null)
			return null;
		try{
			JsonObject jo=new JsonParser().parse(res).getAsJsonObject();
			String access_token=JSONobj.getdtstring(jo, "access_token");
			if(access_token!=null&&!access_token.isEmpty())
				return access_token;
		}catch(Exception e)
		{
			log.error("Accesstokenutil::gettoken error!");
			return null;
		}
		return null;
	}
	
	
	
}
