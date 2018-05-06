package com.guigu.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.config.MainConfigOfAutowired;
import com.atguigu.dao.BookDao;
import com.atguigu.service.BookService;

public class IOCTest_Autowired {
	
	@Test
	public void test01() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
		System.out.println("�������سɹ�");
		
		BookService bookService = (BookService) applicationContext.getBean(BookService.class);
		System.out.println(bookService);
		
		// ����ж��������ô���᷵�� ����������ѡ��ע��
		BookDao bookDao = (BookDao) applicationContext.getBean(BookDao.class);
		System.out.println(bookDao);
		
		applicationContext.close();
	}
	
	@Test
	public void test02() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
		Boss boss = applicationContext.getBean(Boss.class);
		System.out.println(boss);
		Color color = applicationContext.getBean(Color.class);
		System.out.println(color);
		Car car = (Car) applicationContext.getBean("car");
		System.out.println(car);
		applicationContext.close();
	
	}
}
