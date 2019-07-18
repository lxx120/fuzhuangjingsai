package com.match.quartz;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;


import org.quartz.SchedulerFactory;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/** 
 * @author mengly 
 * @version 创建时间：2015年12月22日 下午2:52:02 
 * 类说明 :简易启动quartz job。
 */

public class Quartzjob {
	private  static SchedulerFactory sf=null;//new StdSchedulerFactory();

    static{
    	Quartzjob.sf=new StdSchedulerFactory();
    }
	public static SchedulerFactory instance(){
		return Quartzjob.sf;
	}
	/**
	 * 
	 * @param jobName
	 * @param clz
	 * @param trigger 
	 * @author mengly 
	 * @version 创建时间：2016年3月1日 下午6:33:42
	 */
	public static  void runJob(String jobName, Class<? extends Job> clz,Trigger trigger ) {  
	        try {  
	            Scheduler sched = sf.getScheduler();  
	            JobBuilder jb= JobBuilder.newJob(clz);
	            jb=jb.withIdentity(jobName, jobName+"group");
	            JobDetail jobDetail =jb.build();
	            
	            sched.scheduleJob(jobDetail, trigger);  
	            // 启动  
	            if (!sched.isShutdown()) {  
	                sched.start();  
	            }  
	        } catch (Exception e) {  
	        	e.printStackTrace();
	        }  
	    } 
	 
		public static  void runJob(String jobName, Class<? extends Job> clz,Trigger trigger,Map<String, Object> params ) {  
		        try {  
		            Scheduler sched = sf.getScheduler();  
		            JobBuilder jb= JobBuilder.newJob(clz);
		            jb=jb.withIdentity(jobName, jobName+"group");
		            JobDetail jobDetail =jb.build();
		            JobDataMap jdm=jobDetail.getJobDataMap();
		            for (Entry<String, Object> en : params.entrySet()) {
						jdm.put(en.getKey(), en.getValue());
					}
		            sched.scheduleJob(jobDetail, trigger);  
		            // 启动  
		            if (!sched.isShutdown()) { 
		                sched.start();  
		            }  
		        } catch (Exception e) {  
		        	e.printStackTrace();
		        }  
		    } 
	 
	 
		public static  void runJobNowOnce(String jobName, Class<? extends Job> clz) {  
		        try {  
		            Scheduler sched = sf.getScheduler();  
		            JobBuilder jb= JobBuilder.newJob(clz);
		            jb=jb.withIdentity(jobName, jobName+"group");
		            JobDetail jobDetail =jb.build();
		            
		            @SuppressWarnings("rawtypes")
					TriggerBuilder tb=TriggerBuilder.newTrigger();
		            tb.withIdentity(jobName+"trigger", jobName+"triggergroup").startNow().build();
					Trigger tr=tb.build();
					sched.scheduleJob(jobDetail, tr);  
		            System.out.println("start to do...runjobnowonce");
		            // 启动  
		            if (!sched.isShutdown()) {  
		                sched.start();  
		            }  
		        } catch (Exception e) {  
		        	e.printStackTrace();
		        }  
		    } 
		public static  void runJobNowOnce(String jobName, Class<? extends Job> clz,Map<String, Object> params) {  
	        try {  
	            Scheduler sched = sf.getScheduler();  
	            JobBuilder jb= JobBuilder.newJob(clz);
	            jb=jb.withIdentity(jobName, jobName+"group");
	            JobDetail jobDetail =jb.build();
	            if(params!=null&&!params.isEmpty())
	            {
					jobDetail.getJobDataMap().putAll(params);
	            }
	            @SuppressWarnings("rawtypes")
				TriggerBuilder tb=TriggerBuilder.newTrigger();
	            tb.withIdentity(jobName+"trigger", jobName+"triggergroup").startNow().build();
				Trigger tr=tb.build();
				sched.scheduleJob(jobDetail, tr);  
	            System.out.println("start to do...runjobnowonce");
	            // 启动  
	            if (!sched.isShutdown()) {  
	                sched.start();  
	            }  
	        } catch (Exception e) {  
	        	e.printStackTrace();
	        }  
	    } 
		
		
		public static TriggerState getJobState(String triggername,String triggergroup){
			try {
				Scheduler sched = sf.getScheduler();
				TriggerKey tk=TriggerKey.triggerKey(triggername, triggergroup);
				return sched.getTriggerState(tk);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}  
			return null;
		}
		
		public static  void runJobOnce(String jobName, Class<? extends Job> clz,Date startDate) {  
	        try {  
	            Scheduler sched = sf.getScheduler();  
	            JobBuilder jb= JobBuilder.newJob(clz);
	            jb=jb.withIdentity(jobName, jobName);
	            JobDetail jobDetail =jb.build();
	            TriggerBuilder<Trigger> tb=TriggerBuilder.newTrigger();
	            tb.withIdentity(jobName+"trigger", jobName+"triggergroup").startAt(startDate).build();
				Trigger tr=tb.build();
				sched.scheduleJob(jobDetail, tr);  
	            System.out.println("start to do...runjobnowonce");
	            // 启动  
	            if (!sched.isShutdown()) {  
	                sched.start();  
	            }  
	        } catch (Exception e) {  
	        	e.printStackTrace();
	        }  
	    } 
}
