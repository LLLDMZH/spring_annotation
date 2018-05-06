package com.guigu.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.config.MainConfigOfProfile;

public class IOCTest_Profile {
	
	// 1、使用命令行参数:在虚拟机参数位置加载 -Dspring.profiles.active=test
	// 2.代码的方式激活某种环境
	@SuppressWarnings("resource")
	/**
	 * 默认这个applicationContext不可以用
	 * 	public AnnotationConfigApplicationContext(Class<?>... annotatedClasses) {
		this();
		register(annotatedClasses);
		refresh();
	}
	 */
	@Test
	public void test02() {
		// 1.创建一个applicationContext
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		// 2.设置运行环境
		annotationConfigApplicationContext.getEnvironment().setActiveProfiles("test","dev");
		// 3.注册主配置类
		annotationConfigApplicationContext.register(MainConfigOfProfile.class);
		// 4.启动刷新容器
		annotationConfigApplicationContext.refresh();
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
		annotationConfigApplicationContext.close();
	}
	
	
	@Test
	public void test01() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				MainConfigOfProfile.class);
		String[] names = applicationContext.getBeanNamesForType(DataSource.class);
		for (String name : names) {
			System.out.println(name);
		}
		applicationContext.close();
	}
}
