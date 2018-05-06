package com.guigu.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.config.MainConfigOfLifeCycle;

public class IOCTest_LifeCycle {

	@Test
	public void test01() {
		// 1.����IOC����
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				MainConfigOfLifeCycle.class);
		System.out.println("�����������");

		// ��ʵ����Bean ��ȡ��ʱ�� ���� ���ҳ�ʼ����������������
		// applicationContext.getBean("car");

		applicationContext.close();
	}
}
