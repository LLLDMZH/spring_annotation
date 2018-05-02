package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;

import com.atguigu.bean.Person;

/**
 * 配置类==配置文件
 * @author lenovo
 *
 */
@Configuration()	//告诉Spring 这是一个配置类
@ComponentScan(value = "com.atguigu",includeFilters = {
//		@Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
//		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Person.class}),
//		@Filter(type = FilterType.REGEX, pattern = {"com.atguigu.dao.*"})
		@Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
},useDefaultFilters = false)

/** 
@ComponentScan 
value:指定要扫描的包
	@ComponentScan(value = "com.atguigu")

excludeFilters = Filter[]:指定扫描的时候按照什么规则排除哪些组件
	@ComponentScan(value = "com.atguigu", excludeFilters = {
			@Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
	})


includeFilters = Filter[]:指定扫描的时候只需要包含哪些组件
	@ComponentScan(value = "com.atguigu", includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Person.class})
	}, useDefaultFilters = false)

useDefaultFilters 默认是true扫描包下面的所有类 并注册 类首字母小写
includeFilters 需要配合false
excludeFilters 需要为true

Filter过滤过则:
	1.FilterType.ANNOTATION:按照注解
		@Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
	2.FilterType.ASSIGNABLE_TYPE:按照给定的类型
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Person.class}),
	3.FilterType.ASPECTJ:基本上不会使用 ASPECTJ表达式
	4.FilterType.REGEX:正则表达式
		@Filter(type = FilterType.REGEX, pattern = {"com.atguigu.dao.*"})
	5.FilterType.CUSTOM:自定义规则
	
jdk8 可以多写几次@ComponentScan
如果不是jdk8 那么可以用 
	@ComponentScans(value = {
		@ComponentScan(value = "com.atguigu", includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Person.class})
		}, useDefaultFilters = false)	
	})
*/

public class MainConfig {
	// 给容器中注册一个Bean
	// 类型为返回值的类型，id默认为 方法名作为id
	@Bean
	public Person person() {
		return new Person("lisi",20);
	}
	
}
