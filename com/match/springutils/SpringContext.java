package com.match.springutils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/** 
 * @author mengly 
 * @version 创建时间：2016年3月3日 下午12:32:14 
 * 类说明 
 */
@Service
public class SpringContext implements ApplicationContextAware {
	private static ApplicationContext ctx=null;
	public static ApplicationContext getAppContext()
	{
		return SpringContext.ctx;
	}
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		System.setProperty("dubbo.application.logger", "slf4j");
		SpringContext.ctx=arg0;
	}
}
