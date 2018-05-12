package com.atguigu.jdbc.template;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.aop2.MyService;

public class SpringTemplateTest {
	
	@Test
	public void testJdbc() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/com/atguigu/jdbc/template/applicationContext.xml");
		StudentDao dao = (StudentDao) applicationContext.getBean("studentDao");
		Student selectStudentById = dao.selectStudentById(1);
		System.out.println(selectStudentById);
		List<String> selectStudentNames = dao.selectStudentNames();
		System.out.println(selectStudentNames);
		List<Student> selectStudents = dao.selectStudents();
		System.out.println(selectStudents);
	}
}
