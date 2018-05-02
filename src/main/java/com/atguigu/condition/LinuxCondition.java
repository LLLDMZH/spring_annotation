package com.atguigu.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

// 判断是否为Linux系统
public class LinuxCondition implements Condition{

	/**
	 * ConditionContext:判断条件能使用的上下文环境
	 * AnnotatedTypeMetadata：注释信息
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 是否为Linux系统
		// 1.获取ioc使用的beanFactory
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		// 2.获取类加载器
		ClassLoader classLoader = context.getClassLoader();
		// 3.获取当前环境信息
		Environment environment = context.getEnvironment();
		// 4.获取bean定义的注册类
		BeanDefinitionRegistry registry = context.getRegistry();
		
		// 可以通过虚拟机参数-Dos.name=Linux设置
		String property = environment.getProperty("os.name");
		// 可以判断容器中bean注册情况，也可以给容器中注册bean
		boolean definition = registry.containsBeanDefinition("person");
		if (property.contains("Linux")) {
			return true;
		}
		return false;
	}

}
