package com.guigu.test;

import java.util.Map;

import javax.swing.plaf.synth.SynthStyle;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.atguigu.bean.Color;
import com.atguigu.bean.Person;
import com.atguigu.bean.Rainbow;
import com.atguigu.config.MainConfig;
import com.atguigu.config.MainConfig1;
import com.atguigu.config.MainConfig2;
import com.atguigu.service.BookService;

public class IOCTest {
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
	
	@Test
	public void testImport() {
		System.out.println("=================");
		printBeans(applicationContext);
		Rainbow rainbow = (Rainbow) applicationContext.getBean("rainBow");
		System.out.println(rainbow);
		System.out.println("=================");
		
		
		// ����Bean��ȡ���ǵ���getObject�����Ķ���
		Object bean = applicationContext.getBean("colorFactoryBean");
		System.out.println("bean������"+ bean.getClass());
		Object bean2 = applicationContext.getBean("colorFactoryBean");
		System.out.println(bean == bean2);
		
		// ��ȡ�������� �鿴BeanFactory
		Object beanFactory = applicationContext.getBean("&colorFactoryBean");
		System.out.println("������������"+ beanFactory.getClass());
	}
	
	private void printBeans(AnnotationConfigApplicationContext applicationContext2) {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String names : beanDefinitionNames) {
			System.out.println(names);
		}
	}

	@Test
	public void test00() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig1.class);
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
	
	@Test
	public void test01() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
	
	@Test
	public void test02() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		Color color = (Color) applicationContext.getBean("com.atguigu.bean.Color");
		System.out.println(color);
		
		System.out.println("ioc�����������");
		// Ĭ���ǵ����� Scope�������÷�Χ
		Person person = (Person) applicationContext.getBean("person");
		Person person2 = (Person) applicationContext.getBean("person");
		System.out.println(person == person2);
	}
	
	
	@Test
	public void test03() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		// ��ȡ����ϵͳ
//		String system = System.getProperty("os.name");
//		System.out.println(system);
		Environment environment = applicationContext.getEnvironment();
		String systemName = environment.getProperty("os.name");
		System.out.println(systemName);
		String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
		for (String name : beanNamesForType) {
			System.out.println(name);
		}
		
		Map<String, Person> maps = applicationContext.getBeansOfType(Person.class);
		System.out.println(maps);
	}
}
