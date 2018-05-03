package com.atguigu.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.atguigu.bean.Car;

/**
 * bean的生命周期
 *     bean创建----初始化----销毁过程
 * 容器管理bean的生命周期：
 * 我们可以自定义初始化和销毁方法；容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法。
 * 
 * 构造（对象创建）
 * 		单实例：在容器启动的时候创建对象
 * 		多实例：在每次获取的时候创建对象
 * 初始化：
 * 	    对象创建完成，并属性赋值好，调用初始化方法
 * 
 * 销毁：
 *    单实例Bean：在容器关闭的时候销毁
 *    多实例Bean：容器不会管理这个Bean，容器不会调用销毁方法。需要手动调用销毁。
 * 
 * 1)、指定初始化和销毁方法：
 * 		通过@Bean指定init-method="" destroy-method=""
 * 
 * @author lenovo
 *
 */
@Configuration
public class MainConfigOfLifeCycle {
	
	//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Bean(initMethod="init", destroyMethod="destroy")
	public Car car() {
		return new Car();
	}
}
