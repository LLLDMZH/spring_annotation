package com.atguigu.ext;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	/**
	 * Modify the application context's internal bean factory after its standard
	 * initialization. All bean definitions will have been loaded, but no beans
	 * will have been instantiated yet. This allows for overriding or adding
	 * properties even to eager-initializing beans.
	 * @param beanFactory the bean factory used by the application context
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
			System.out.println("MyBeanFactoryPostProcessor.postProcessBeanFactory()");
			int count = beanFactory.getBeanDefinitionCount();
			String[] names = beanFactory.getBeanDefinitionNames();
			System.out.println("当前BeanFactory中有" + count + "个Bean");
			System.out.println(Arrays.asList(names));
	}

}
