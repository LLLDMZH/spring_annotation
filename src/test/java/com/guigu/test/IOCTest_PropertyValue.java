package com.guigu.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfigOfPropertyValues;

public class IOCTest_PropertyValue {
	private AnnotationConfigApplicationContext applicationContext =  new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

	@SuppressWarnings("resource")
	@Test
	public void test01() {
		printBeans(applicationContext);
		// 关闭容器
		Person person = (Person) applicationContext.getBean("person");
		System.out.println(person);
		
		// 获取运行时环境
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		String property = environment.getProperty("person.nickName");
		System.out.println(property);
		applicationContext.close();
	}
	
	
	private void printBeans(AnnotationConfigApplicationContext applicationContext2) {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String names : beanDefinitionNames) {
			System.out.println(names);
		}
	}
}
