package com.bee.springboot.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class  SpringContextUtil implements ApplicationContextAware {
	static ApplicationContext ctx ;
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		ctx = arg0;
		
	}
	public static  Object getBean(String s) {
		return ctx.getBean(s);
	}

	public static <T> T getBean(String s, Class<T> class1)
	{
		return ctx.getBean(s,class1);
	}

	public static <T> T getBean(Class<T> class1)
	{
		return ctx.getBean(class1);
	}
	
}
