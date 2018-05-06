package com.guigu.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.config.MainConfigOfProfile;

public class IOCTest_Profile {
	
	// 1��ʹ�������в���:�����������λ�ü��� -Dspring.profiles.active=test
	// 2.����ķ�ʽ����ĳ�ֻ���
	@SuppressWarnings("resource")
	/**
	 * Ĭ�����applicationContext��������
	 * 	public AnnotationConfigApplicationContext(Class<?>... annotatedClasses) {
		this();
		register(annotatedClasses);
		refresh();
	}
	 */
	@Test
	public void test02() {
		// 1.����һ��applicationContext
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		// 2.�������л���
		annotationConfigApplicationContext.getEnvironment().setActiveProfiles("test","dev");
		// 3.ע����������
		annotationConfigApplicationContext.register(MainConfigOfProfile.class);
		// 4.����ˢ������
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
