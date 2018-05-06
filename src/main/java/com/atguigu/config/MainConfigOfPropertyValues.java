package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.atguigu.bean.Person;

// 使用PropertySource读取外部配置文件中的k/v保存到运行的环境变量中;加载完外部配置文件以后使用${}取出配置文件的值
@PropertySource(value = {"classpath:person.properties"})
// 可以有多个PropertySource 也可以用PropertySources
//@PropertySource(value = {"file:/D:/java-project/myblog/spring-annotation/src/main/resources/person.properties"})
@Configuration
public class MainConfigOfPropertyValues {
	
	@Bean
	public Person person() {
		return new Person();
	}
}
