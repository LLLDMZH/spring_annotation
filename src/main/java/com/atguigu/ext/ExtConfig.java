package com.atguigu.ext;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.atguigu.bean.Car;


/**
 * 扩展原理：
 * 	BeanPostProcessor:bean后处理器,bean创建对象初始化前后进行拦截工作
 * 	BeanFactoryPostProcessor:beanFactory的后置处理器
 * 			在BeanFactory标准初始化后调用：
				All bean definitions will have been loaded, but no beans
				will have been instantiated yet. 
				
	1、ioc容器创建对象
	2、invokeBeanFactoryPostProcessors(beanFactory);
	3、如何找到所有的BeanFactoryPostProcessor并执行他们的方法
			1、直接在BeanFactory中 找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
			2、在初始化创建其他组件前面执行
 * @author lenovo
 *
 */

@ComponentScan("com.atguigu.ext")
@Configuration
public class ExtConfig {
	
	@Bean
	public Car car() {
		return new Car();
	}
}
