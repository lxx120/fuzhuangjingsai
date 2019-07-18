package com.match.test;

import java.util.List;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.match.pinyin.utils.Pinyinmethod;

public class FlowableTest {
	private Logger log=LogManager.getLogger();
	@Test
	public void test11(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationcontext.xml");
		RepositoryService repositoryService=ac.getBean(RepositoryService.class);
		RuntimeService runtimeService=ac.getBean(RuntimeService.class);
		log.info("start to find pds .....................................................................");
		List<ProcessDefinition> pds=repositoryService.createProcessDefinitionQuery().list();
		pds.forEach((pd)->{
			ProcessInstance pi=runtimeService.startProcessInstanceById(pd.getId());
		});
	}
	
	@Test
	public void test12(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationcontext.xml");
		TaskService taskService=ac.getBean(TaskService.class);
		List<Task> tks=taskService.createTaskQuery().taskAssignee("[mengly]").list();
		log.info("tks size is :::"+tks.size());
		System.out.println("tks size println  is :::"+tks.size());
		tks.forEach((pd)->{
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+pd.getName()+"<<<<<<<<<<<<");
			System.out.println(">>>>>>>>>>>>>>>>>> println  is :::>>>>>>>>>>>>>>>>>>"+pd.getName()+"<<<<<<<<<<<<");

		});
	}
	
	@Test
	public void test121(){
		Pinyinmethod pp = new Pinyinmethod();
		String s = pp.ToPinyin("刘晓轩");
		System.out.println(s);
		
		System.out.println(System.currentTimeMillis());
	}
	
	public static void main(String[] args) {
		int groupnum = 8;  //分组个数
		int sumworks = 68;  //作品个数
//		int sumteacher = Integer.parseInt(map.get("sumteacher").toString()); //老师个数
		
		int pagesize = sumworks/groupnum;
		int mo = sumworks%groupnum;
		System.out.println(pagesize);
		System.out.println(mo);
	}
}
