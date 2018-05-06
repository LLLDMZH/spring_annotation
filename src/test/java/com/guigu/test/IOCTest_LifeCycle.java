package com.guigu.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.config.MainConfigOfLifeCycle;

public class IOCTest_LifeCycle {

	@Test
	public void test01() {
		// 1.创建IOC容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				MainConfigOfLifeCycle.class);
		System.out.println("容器创建完成");

		// 多实例的Bean 获取的时候 创建 并且初始化，但不进行销毁
		// applicationContext.getBean("car");

		applicationContext.close();
	}
}
