package com.match.org.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.match.baciss.cl.POIChangLiang;
import com.match.baciss.cl.PouUtil;
import com.match.baciss.cl.UserChangLiang;
import com.match.judges.mapper.AssessDaiWorkMapper;
import com.match.judges.mapper.AssessRoundsMapper;
import com.match.judges.model.AssessDaiWork;
import com.match.org.mapper.CollegeMapper;
import com.match.org.mapper.DepartmentMapper;
import com.match.org.mapper.EnterpriseMapper;
import com.match.org.model.College;
import com.match.org.model.Department;
import com.match.org.model.Enterprise;
import com.match.org.service.PoiService;
import com.match.proposition.mapper.CompetitionMapper;
import com.match.proposition.mapper.ThemeMapper;
import com.match.proposition.mapper.ThemeSignupMapper;
import com.match.proposition.mapper.ThemeWorkMapper;
import com.match.proposition.model.Theme;
import com.match.proposition.model.ThemeSignup;
import com.match.proposition.model.ThemeWork;
import com.match.springutils.SpringContext;

@Service
public class PoiServiceImpl implements PoiService {

	@Autowired
	private  CompetitionMapper competition;
	
	@Autowired
	private  EnterpriseMapper enterpriseMapper;
	
	@Autowired
	private  AssessRoundsMapper  assessRoundsMapper;
	
	@Override
	public int writeExcle(MultipartFile file,long divisionid) throws Exception {
		// TODO Auto-generated method stub
		PouUtil po = new PouUtil();
		List<String[]> filelist = po.readExcel(file);
		if(filelist!=null && filelist.size()>0)
		{
			//添加一个企业
			Enterprise enterprise = new Enterprise();
			enterprise.setMtime(UserChangLiang.mtime());
			enterprise.setName(POIChangLiang.name);
			enterpriseMapper.insertSelective(enterprise);
			//查询当前比赛
			Map<String,Object>  commap = competition.findCurrnetCompetition3();
			if(commap==null)
			{
				return 2;
			}
			//查询当前伦次
			Map<String,Object> asmap = assessRoundsMapper.findAR(Long.parseLong(commap.get("id").toString()),0);
			if(asmap==null)
			{
				return 3;
			}
			saveUserInfoList(divisionid,filelist, enterprise.getId(),Long.parseLong(commap.get("id").toString()),Long.parseLong(asmap.get("id").toString()));
		}
		return 0;
	}
	
	public  long  addCollegeAndDepartment(String collegecode,String college,String department)  throws  Exception
	{
		//查询大学是否存在
		ApplicationContext ac = SpringContext.getAppContext();
		CollegeMapper asss = ac.getBean(CollegeMapper.class);
		DepartmentMapper asss1 = ac.getBean(DepartmentMapper.class);
		Map<String,Object>  map = asss.findCollegeExist(collegecode, college);
		if(map!=null)
		{
			Map<String,Object> map1 = asss1.findDepartmentExist(department,Long.parseLong(map.get("id").toString()));
			if(map1==null)
			{
				if(!StringUtils.isBlank(department))
				{
					Department department2 = new Department();
					department2.setMtime(UserChangLiang.mtime());
					department2.setDename(department);
					department2.setCollegeid(Long.parseLong(map.get("id").toString()));
					asss1.insertSelective(department2);
				}
				
			}
			
			return Long.parseLong(map.get("id").toString());
		}
		else
		{
			College college2 = new  College();
			college2.setName(college);
			college2.setCode(collegecode);
			college2.setMtime(UserChangLiang.mtime());
			asss.insertSelective(college2);
			
			if(!StringUtils.isBlank(department))
			{
				Department department2 = new Department();
				department2.setMtime(UserChangLiang.mtime());
				department2.setDename(department);
				department2.setCollegeid(college2.getId());
				asss1.insertSelective(department2);
			}
			
			return college2.getId();
		}
	}
	
	public  long  addTheme(String themecode,String themename,long competitionid,long enterpriseid,long divisionid)  throws  Exception
	{
		ApplicationContext ac = SpringContext.getAppContext();
		ThemeMapper asss = ac.getBean(ThemeMapper.class);
		Map<String,Object> map = asss.findThemeExist(themecode, themename);
		
		if(map==null)
		{
			Theme theme = new Theme();
			theme.setTitle(themename);
			theme.setMtime(UserChangLiang.mtime());
			theme.setCompetitionid(competitionid);
			theme.setDivisionid(divisionid);
			theme.setThemecode(themecode);
			theme.setEnterpriseid(enterpriseid);
			asss.insertSelective(theme);
			return theme.getId();
		}
		return Long.parseLong(map.get("id").toString());
	}
	
	
	public  long  addthemesignup(String phone,String realname,String twcode,long themeid,String teacher,long competitionid,String workType,String teacherphone,Date signtime,long enterpriseid,long collegeid,String title,long divisionid)  throws  Exception
	{
		ApplicationContext ac = SpringContext.getAppContext();
		ThemeSignupMapper asss = ac.getBean(ThemeSignupMapper.class);
		ThemeWorkMapper asss1 = ac.getBean(ThemeWorkMapper.class);
		//报名
		ThemeSignup themeSignup = new ThemeSignup();
		themeSignup.setCompetitionid(competitionid);
		themeSignup.setMtime(UserChangLiang.mtime());
		themeSignup.setIsupload(0);
		themeSignup.setWorkType(workType);
		themeSignup.setThemeid(themeid);
		themeSignup.setTeacherphone(teacherphone);
		themeSignup.setDivisionid(divisionid);
		themeSignup.setSignuptime(signtime);
		themeSignup.setTeacher(teacher);
		themeSignup.setCompetitionType(1);
		themeSignup.setPhone(phone);
		themeSignup.setRealname(realname);
		themeSignup.setStitle(title);
		themeSignup.setStwcode(twcode);
		asss.insertSelective(themeSignup);
		
		//提交作品
		ThemeWork themeWork = new ThemeWork();
		themeWork.setMtime(UserChangLiang.mtime());
		themeWork.setThemeid(themeid);
		themeWork.setEnterpriseid(enterpriseid);
		themeWork.setCompetitionid(competitionid);
		themeWork.setDivisionid(divisionid);
		themeWork.setCollegeid(collegeid);
		themeWork.setTitle(title);
		themeWork.setViews(0);
		themeWork.setTwcode(twcode);
		themeWork.setThemesignupid(themeSignup.getId());
		asss1.insertSelective(themeWork);
		return themeWork.getId();
	}

	
	public  int  add(List<String[]> filelist,long enterpriseid,long comid,long arid,long divisionid)  throws Exception
	{
		for (int i = 0; i < filelist.size(); i++) {
			String [] filea = filelist.get(i);
			String twcode = filea[3];
			String [] code = twcode.split("-");
			//命题编号
			String themecode = code[0].substring(1,3);
			//赛区编号
//			String divisioncode = code[1];
			//大学编号
			String collegecode = code[2];
			//命题类别
			String  type = filea[1];
			//命题名称
			String  themename = filea[2];
			//作品名称
			String  title = filea[4];
			//作者
			String  username = filea[5];
			//作者电话
			String  phone = filea[6];
			//指导老师
			String  teacher = filea[7];
			//老师电话
			String  teacherphone = filea[8];
			//学校
			String  college = filea[9];
			//院系
			String  department = filea[10];
			//时间
			String  time = filea[11];
			//提价时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date signtime = UserChangLiang.mtime();
			
					//添加学校以及院系
					long collegeid = addCollegeAndDepartment(collegecode, college, department);
					
					//添加命题
					long themeid = addTheme(themecode, themename,comid,enterpriseid,divisionid);
					
					//报名
					long themeworkid = addthemesignup(phone,username,twcode,themeid, teacher, comid, type, teacherphone, signtime,enterpriseid,collegeid,title,divisionid);
					
					//添加到带评判
					ApplicationContext ac = SpringContext.getAppContext();
					AssessDaiWorkMapper asss = ac.getBean(AssessDaiWorkMapper.class);
					AssessDaiWork assessDaiWork = new AssessDaiWork();
					assessDaiWork.setCompetitionid(comid);
					assessDaiWork.setDivisionid(divisionid);
					assessDaiWork.setMtime(UserChangLiang.mtime());
					assessDaiWork.setWorkid(themeworkid);
					assessDaiWork.setArid(arid);
					asss.addAssessDaiWork(assessDaiWork);
		}
		return 0;
	}
	
	
	
	public int saveUserInfoList(long divisionid,List<String[]> list, Long enterpriseid,long comid,long arid) {
        Integer successCount = 0;
        int count = 5000;// 一个线程处理50条数据
        int listSize = list.size();// 数据集合大小
        int runThreadSize = (listSize / count) + 1; // 开启的线程数
        List<String[]> newlist = null;// 存放每个线程的执行数据
        ExecutorService executor = Executors.newFixedThreadPool(runThreadSize);// 创建一个线程池，数量和开启线程的数量一样
 
        // 创建两个个计数器
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(runThreadSize);
        // 循环创建线程
        for (int i = 0; i < runThreadSize; i++) {
            if ((i + 1) == runThreadSize) {
                int startIndex;
                startIndex = (i * count);
                int endIndex = list.size();
                newlist = list.subList(startIndex, endIndex);
            } else {
                int startIndex = (i * count);
                int endIndex = (i + 1) * count;
                newlist = list.subList(startIndex, endIndex);
            }
 
            //线程类，处理数据持久化
            try {
				MyThread mythead = new MyThread(newlist, begin, end,enterpriseid,comid,arid,divisionid);
            	//这里执行线程的方式是调用线程池里的executor.execute(mythead)方法。
            	executor.execute(mythead);
            } catch (Exception e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            }
        }
        try {
        	begin.countDown();
        	end.await();
        	 //执行完关闭线程池
            executor.shutdown();
        } catch (InterruptedException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
       
        return successCount;

	}
	
	
	private static class MyThread implements Runnable {
		private List<String[]> list;
		private CountDownLatch begin;
		private CountDownLatch end;
		private long enterpriseid;
		private long comid;
		private long arid;
		private long divisionid;
		
		public MyThread(List<String[]> list,CountDownLatch begin,CountDownLatch end, Long enterpriseid,Long comid,Long arid,long divisionid){
			this.list = list;
			this.begin = begin;
			this.end = end;
			this.enterpriseid = enterpriseid;
			this.comid = comid;
			this.arid = arid;
			this.divisionid = divisionid;
			}

		@Override
		public void run() {
			try {
				for (int i = 0; i < list.size(); i++) {
					// 这里还要说一下，，由于在实质项目中，当处理的数据存在等待超时和出错会使线程一直处于等待状态
					// 这里只是处理简单的，
					System.out.println(list.get(i));
				}
				PoiServiceImpl PoiServiceImpl = new PoiServiceImpl();
				try {
					PoiServiceImpl.add(list, enterpriseid,comid,arid,divisionid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 执行完让线程直接进入等待
				begin.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// 这里要主要了，当一个线程执行完 了计数要减一不要这个线程会被一直挂起
				// ，end.countDown()，这个方法就是直接把计数器减一的
				end.countDown();
			}

		}
	}
}
