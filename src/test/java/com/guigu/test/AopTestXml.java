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
		// ���ȡ����Service�Ĵ��� ��ProxyFactoryBean������
		SomeService proxy = (SomeService) applicationContext.getBean("serviceProxy");
		System.out.println(proxy.getClass());
		proxy.doSome();
		// �������toString����
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
	
	//ֱ�Ӹ����е�bean������ ֻҪ����advisor�Ĺ���
	@Test
	public void testDefaultAdvisorAutoProxyCreator() {
		// �����͸����е�bean����ǿ��
		SomeService service = (SomeService) applicationContext.getBean("someService");
		// ���Ǵ���
		service.doOther();
		System.out.println("------------------");
		service.doSome();
		System.out.println("======================");
		SomeService service2 = (SomeService) applicationContext.getBean("someService2");
		service2.doOther();
		System.out.println("------------------");
		service2.doSome();
	}
	
	
	// ��ָ����bean �����Ƕ�� ��������
	@Test
	public void testBeanNameAutoProxyCreator() {
		SomeService service = (SomeService) applicationContext.getBean("someService");
		System.out.println(service.getClass());
		// ���Ǵ���
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
