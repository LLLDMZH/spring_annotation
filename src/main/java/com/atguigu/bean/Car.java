package com.atguigu.bean;

public class Car {

	public Car() {
		System.out.println("Car Constructor");
	}
	
	
	public void init() {
		
		// 数据源初始化的时候 很多的属性赋值
		System.out.println("Car init");
	}
	
	public void destroy() {
		// 销毁的时候也要关闭数据源的连接
		System.out.println("Car destroy");
	}
}
