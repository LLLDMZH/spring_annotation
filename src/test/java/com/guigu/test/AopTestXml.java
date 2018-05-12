package com.guigu.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.service.SomeService;

public class AopTestXml {
	
	private ClassPathXmlApplicationContext applicationContext;

	@Before
	public void beforeTest() {
		applicationContext = new ClassPathXmlApplicationContext("aop1.xml");
		
	}
	
	@Test
	public void testMethodBeforeAdvice() {
		// 你获取的是Service的代理 用ProxyFactoryBean创建的
		SomeService proxy = (SomeService) applicationContext.getBean("serviceProxy");
		System.out.println(proxy.getClass());
		proxy.doSome();
		// 还会代理toString方法
		proxy.toString();
	}
	
	@After
	public void afterTest() {
		applicationContext.close();
	}
	
	@Test
	public void testMethodBeforeAndAfter() {
		SomeService proxy = (SomeService) applicationContext.getBean("serviceProxy2");
		proxy.doSome();
	}
	
	
	// NameMatchMethodPointCutAdvisor
	@Test
	public void testAdvisorProxyBeanFactory() {
		SomeService proxy = (SomeService) applicationContext.getBean("serviceProxy3");
		proxy.doOther();
		System.out.println("------------------");
		proxy.doSome();
	}
	
	@Test
	public void testAdvisorProxyBeanFactory2() {
		SomeService proxy = (SomeService) applicationContext.getBean("serviceProxy4");
		proxy.doOther();
		System.out.println("------------------");
		proxy.doSome();
	}
	
	//直接给所有的bean安排上 只要符合advisor的规则
	@Test
	public void testDefaultAdvisorAutoProxyCreator() {
		// 这样就给所有的bean都增强了
		SomeService service = (SomeService) applicationContext.getBean("someService");
		// 不是代理
		service.doOther();
		System.out.println("------------------");
		service.doSome();
		System.out.println("======================");
		SomeService service2 = (SomeService) applicationContext.getBean("someService2");
		service2.doOther();
		System.out.println("------------------");
		service2.doSome();
	}
	
	
	// 给指定的bean 可以是多个 插入切面
	@Test
	public void testBeanNameAutoProxyCreator() {
		SomeService service = (SomeService) applicationContext.getBean("someService");
		System.out.println(service.getClass());
		// 不是代理
		service.doOther();
		System.out.println("------------------");
		service.doSome();
		System.out.println("======================");
		SomeService service2 = (SomeService) applicationContext.getBean("someService2");
		service2.doOther();
		System.out.println("------------------");
		service2.doSome();
	}
}
