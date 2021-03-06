package com.atguigu.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.atguigu.bean.Rainbow;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	
	/**
	 * AnnotationMetadata 当前类的注解信息 其他信息
	 * BeanDefinitionRegistry: BeanDefinition注册类
	 * 
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// 手工注册
		boolean definition = registry.containsBeanDefinition("com.atguigu.bean.Red");
		boolean definition2 = registry.containsBeanDefinition("com.atguigu.bean.Blue");
		if (definition && definition2) {
			// 指定Bean定义信息（Bean的类型作用域等信息）
			RootBeanDefinition beanDefinition = new RootBeanDefinition(Rainbow.class);
			// 注册一个Bean,指定bean名
			registry.registerBeanDefinition("rainBow", beanDefinition);
		}
	}
}
