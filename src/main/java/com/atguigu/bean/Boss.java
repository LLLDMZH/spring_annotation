package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 默认加载ioc容器中的组件，容器启动会调用无参构造器创建对象，再进行初始化赋值等操作
@Component
public class Boss {
	
	// @Autowired
	private Car car;
	
	
	public Car getCar() {
		return car;
	}

	
	// 构造器要用的组件，都是从容器中获取
	// 如果只有一个构造器 那么创建Boss对象必须调用
//	@Autowired
//	public Boss(Car car) {
//		super();
//		this.car = car;
//		System.out.println("Boss 的有参构造器");
//	}
//	
	
	
	public Boss(@Autowired Car car) {
		super();
		this.car = car;
		System.out.println("Boss 的有参构造器");
	}

	//	@Autowired 
	// 标注在方法，Spring容器创建当前对象，就会调用方法，完成赋值。
	// 方法使用的参数，自定义类型的值从ioc容器中获取
	public void setCar(Car car) {
		this.car = car;
	}


	@Override
	public String toString() {
		return "Boss [car=" + car + "]";
	}
	
}
