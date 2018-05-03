package com.atguigu.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.atguigu.bean.Car;

/**
 * bean����������
 *     bean����----��ʼ��----���ٹ���
 * ��������bean���������ڣ�
 * ���ǿ����Զ����ʼ�������ٷ�����������bean���е���ǰ�������ڵ�ʱ�������������Զ���ĳ�ʼ�������ٷ�����
 * 
 * ���죨���󴴽���
 * 		��ʵ����������������ʱ�򴴽�����
 * 		��ʵ������ÿ�λ�ȡ��ʱ�򴴽�����
 * ��ʼ����
 * 	    ���󴴽���ɣ������Ը�ֵ�ã����ó�ʼ������
 * 
 * ���٣�
 *    ��ʵ��Bean���������رյ�ʱ������
 *    ��ʵ��Bean����������������Bean����������������ٷ�������Ҫ�ֶ��������١�
 * 
 * 1)��ָ����ʼ�������ٷ�����
 * 		ͨ��@Beanָ��init-method="" destroy-method=""
 * 
 * @author lenovo
 *
 */
@Configuration
public class MainConfigOfLifeCycle {
	
	//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Bean(initMethod="init", destroyMethod="destroy")
	public Car car() {
		return new Car();
	}
}
