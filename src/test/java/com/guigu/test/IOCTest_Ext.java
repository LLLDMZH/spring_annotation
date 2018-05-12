package com.guigu.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.ext.ExtConfig;

public class IOCTest_Ext {
	
	@Test
	public void test01() {
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(ExtConfig.class);
		applicationContext.close();
	}
	
}
