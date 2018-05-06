package com.atguigu.config;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
 *
 *	postProcessBeforeInitialization：在任何的初始化之前执行
 *	初始化：
 * 	    对象创建完成，并属性赋值好，调用初始化方法
 *	postProcessAfterInitialization：初始化之后执行    		
 *  
 *  销毁：
 *    单实例Bean：在容器关闭的时候销毁
 *    多实例Bean：容器不会管理这个Bean，容器不会调用销毁方法。需要手动调用销毁。
 * 
 * 	BeanPostProcessor原理：
 * 
 * 		1)、populateBean(beanName, mbd, instanceWrapper);给Bean的属性值赋好
 * 			遍历得到容器中所有的BeanPostProcessor，挨个执行postProcessBeforeInitialization
 * 			一旦返回null就跳出for循环，不会执行后面的postProcessBeforeInitialization
 * 		2)、	下面都属于整个初始化过程。
 * 			{
				1.applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
				2.invokeInitMethods(beanName, wrappedBean, mbd); 自定义初始化方法
				3.applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
			}
		
 * 
 * 1)、指定初始化和销毁方法：
 * 		通过@Bean指定init-method="" destroy-method=""
 * 
 * 2)、通过让Bean实现InitializingBean接口(定义初始化逻辑)，
 * 	   DisposableBean接口(定义销毁逻辑)
 * 3)、使用JSR250:
 * 		@PostConstruct： 标注在方法位置。
 * 		@PreDestroy: Bean被移除之前。
 * 4)、BeanPostProcessor【interface】:bean的后置处理器
 * 		在bean初始化前后进行一些处理工作：
 * 		postProcessBeforeInitialization：在任何的初始化之前执行
 *	 	postProcessAfterInitialization：初始化之后执行    		
 *
 *
 *
 *	Spring底层对BeanPostProcessor底层的使用
 *		bean赋值，注入其他组件，@Autowired,生命周期注解功能，@Async,
 * @author lenovo
 *
 */
@Configuration
@ComponentScan("com.atguigu.bean")
public class MainConfigOfLifeCycle {
	
	//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Bean(initMethod="init", destroyMethod="destroy")
	public Car car() {
		return new Car();
	}
}
