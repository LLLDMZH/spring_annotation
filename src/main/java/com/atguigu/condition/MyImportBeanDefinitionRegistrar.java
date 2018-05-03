package com.atguigu.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.atguigu.bean.Rainbow;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	
	/**
	 * AnnotationMetadata ��ǰ���ע����Ϣ ������Ϣ
	 * BeanDefinitionRegistry: BeanDefinitionע����
	 * 
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// �ֹ�ע��
		boolean definition = registry.containsBeanDefinition("com.atguigu.bean.Red");
		boolean definition2 = registry.containsBeanDefinition("com.atguigu.bean.Blue");
		if (definition && definition2) {
			// ָ��Bean������Ϣ��Bean���������������Ϣ��
			RootBeanDefinition beanDefinition = new RootBeanDefinition(Rainbow.class);
			// ע��һ��Bean,ָ��bean��
			registry.registerBeanDefinition("rainBow", beanDefinition);
		}
	}
}
