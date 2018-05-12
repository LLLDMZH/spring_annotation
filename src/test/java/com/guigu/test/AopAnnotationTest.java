package com.guigu.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.aop2.MyService;

public class AopAnnotationTest {
	@Test
	public void test() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop2.xml");
		MyService service1 = (MyService) applicationContext.getBean("myServiceImpl1");
		service1.my();
		System.out.println("======================");
		MyService service2 = (MyService) applicationContext.getBean("myServiceImpl2");
		service2.my();
	}
	
	// testaopxml
	@Test
	public void test2() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/com/atguigu/aop3/aop3.xml");
		com.atguigu.aop3.MyService service1 = (com.atguigu.aop3.MyService) applicationContext.getBean("myservice1");
		service1.my();
		System.out.println("======================");
		com.atguigu.aop3.MyService service2 = (com.atguigu.aop3.MyService) applicationContext.getBean("myservice2");
		service2.my();
	}
}
