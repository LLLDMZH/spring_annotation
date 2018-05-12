package com.atguigu.ext;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.atguigu.bean.Car;


/**
 * ��չԭ��
 * 	BeanPostProcessor:bean������,bean���������ʼ��ǰ��������ع���
 * 	BeanFactoryPostProcessor:beanFactory�ĺ��ô�����
 * 			��BeanFactory��׼��ʼ������ã�
				All bean definitions will have been loaded, but no beans
				will have been instantiated yet. 
				
	1��ioc������������
	2��invokeBeanFactoryPostProcessors(beanFactory);
	3������ҵ����е�BeanFactoryPostProcessor��ִ�����ǵķ���
			1��ֱ����BeanFactory�� �ҵ�����������BeanFactoryPostProcessor���������ִ�����ǵķ���
			2���ڳ�ʼ�������������ǰ��ִ��
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
