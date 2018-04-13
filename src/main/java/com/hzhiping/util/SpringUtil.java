package com.hzhiping.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 
 * @title:spring的工具类
 * @description:spring读取配置文件的工具类
 * @author:hzhiping
 * @since:2018年4月11日 下午8:22:23
 */
public class SpringUtil {

	private static Logger logger = LoggerFactory.getLogger(SpringUtil.class);
	/** Spring框架应用上下文对象 */
	private static ApplicationContext factory = getApplicationContext();

	static {
		getApplicationContext();
	}

	public static void setFactoryBean(ApplicationContext factory) {
		SpringUtil.factory = factory;
	}

	/**
	 * 获得Spring框架应用上下文对象
	 * 
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		// 判断如果ApplicationContext的对象==null
		if (factory == null) {
			if (logger.isDebugEnabled())
				logger.debug("Init Spring's ApplicationContext...");
			try {
				factory = new ClassPathXmlApplicationContext("application.xml");
			} catch (Exception e1) {
				if (logger.isDebugEnabled())
					logger.debug("err = " + e1.getMessage());
				e1.printStackTrace();
			}
		}
		// 返回ApplicationContext
		return factory;
	}
	
	public static void main(String[] args) {
		System.out.println(getApplicationContext());
	}
}