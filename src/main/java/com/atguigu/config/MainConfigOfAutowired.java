package com.atguigu.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.dao.BookDao;

/**
 * 自动装配；
 * 		Spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值
 * 1)、@Autowired:自动注入：
 * 		1)、默认优先按照类型去容器中找对应的组件: applicationContext.getBean(BookDao.class);
 * 		2)、如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 * 								applicationContext.getBean("bookDao")
 * 		3)、@Qualifier("bookDao"):使用@Qualifier指定需要装配的组件的id 而不是使用属性名	
 * 		4)、自动装配默认一定要将属性赋值好，没有就会报错; 属性require=false
 * 		5)、@Primary:让Spring 进行自动装配的时候默认使用首选的Bean
 * 			也可以继续使用@Qualifier指定需要装配的Bean
 * 		找到就赋值
 * 		BookService {
 * 			@Autowired
 * 			BookDao bookDao;	
 * 		}
 * 
 * 2）、Spring还支持@Resource(JSR250)和@Inject(JSR330)[Java规范注解]
 * 		@Resource：
 * 				可以和@Autowired一样实现自动装配功能，默认是按照组件名称进行装配
 * 				没有支持@Primary功能； 没有支持@Autowired(required=false)
 * 		@Inject:
 * 			          需要导入javax.inject的包和AutoWired @Primary生效，没有required=false的功能
 * 
 *      @Autowired:Spring定义的；@Resource、@Inject都是Java规范
 * 		
 * 
 * AutowiredAnnotationBeanPostsProcessor:解析完成自动装配的功能。
 * 
 * 3)、@Autowired:构造器、参数、方法、属性；都是从容器中获取参数组件的值
 * 		1)、标注在方法之上:@Bean + 方法参数。参数从容器中获取；默认不写@Autowired效果是一样的，都能自动装配	
 * 		2)、标注在构造器上，如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略
 * 		3)、放在参数位置
 * 
 * 4)、自定义组件想要使用Spring容器底层的一些组件
 * 		(ApplicationContext，BeanFactory，xxx)
 * 		组件实现xxxAware即可；在创建对象的时候，会调用接口规定的方法注入相关组件
 * 		把Spring底层的一些组件注入到自定义的Bean中
 * 		xxxAware：功能使用xxxProcessor处理
 * 
 * @author lenovo
 *
 */
@Configuration
@ComponentScan({"com.atguigu.service", "com.atguigu.controller",
		"com.atguigu.dao","com.atguigu.bean"})
public class MainConfigOfAutowired {
	
	// 提供两个BookDao.class类型的Bean
	
	@Primary
	@Bean("bookDao2")
	public BookDao bookDao() {
		BookDao bookDao = new BookDao();
		bookDao.setLable("2");
		return bookDao;
	}
	
	
	/**
	 * @Bean标注的方法创建对象的时候，方法参数的值从容器中获取
	 * @return
	 */
	@Bean
	public Color color(Car car) {
		Color color = new Color();
		color.setCar(car);
		return color;
	}
	
}
