package com.atguigu.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;



@Component
public class Dog implements ApplicationContextAware{//Ϊ���ע��ioc����
	
	private ApplicationContext applicationContext;
	
	public Dog() {
		System.out.println("dog constructor...");
	}
	
	// ���󴴽�����ֵ֮�����
	@PostConstruct
	public void init() {
		System.out.println("dog postConstruct");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("dog preDestroy");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}