package com.guigu.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.tx.TxConfig;
import com.atguigu.tx.UserService;

public class IOCTest_Tx {
	
	@Test
	public void test01() {
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(TxConfig.class);
		UserService userService = (UserService) applicationContext.getBean("userService");
		userService.insertUser();
		applicationContext.close();
	}
	
}
