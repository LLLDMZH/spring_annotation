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
 * ������==�����ļ�
 * @author lenovo
 *
 */
@Configuration()	//����Spring ����һ��������
@ComponentScan(value = "com.atguigu",includeFilters = {
//		@Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
//		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Person.class}),
//		@Filter(type = FilterType.REGEX, pattern = {"com.atguigu.dao.*"})
		@Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
},useDefaultFilters = false)

/** 
@ComponentScan 
value:ָ��Ҫɨ��İ�
	@ComponentScan(value = "com.atguigu")

excludeFilters = Filter[]:ָ��ɨ���ʱ����ʲô�����ų���Щ���
	@ComponentScan(value = "com.atguigu", excludeFilters = {
			@Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
	})


includeFilters = Filter[]:ָ��ɨ���ʱ��ֻ��Ҫ������Щ���
	@ComponentScan(value = "com.atguigu", includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Person.class})
	}, useDefaultFilters = false)

useDefaultFilters Ĭ����trueɨ�������������� ��ע�� ������ĸСд
includeFilters ��Ҫ���false
excludeFilters ��ҪΪtrue

Filter���˹���:
	1.FilterType.ANNOTATION:����ע��
		@Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
	2.FilterType.ASSIGNABLE_TYPE:���ո���������
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Person.class}),
	3.FilterType.ASPECTJ:�����ϲ���ʹ�� ASPECTJ���ʽ
	4.FilterType.REGEX:������ʽ
		@Filter(type = FilterType.REGEX, pattern = {"com.atguigu.dao.*"})
	5.FilterType.CUSTOM:�Զ������
	
jdk8 ���Զ�д����@ComponentScan
�������jdk8 ��ô������ 
	@ComponentScans(value = {
		@ComponentScan(value = "com.atguigu", includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Person.class})
		}, useDefaultFilters = false)	
	})
*/

public class MainConfig {
	// ��������ע��һ��Bean
	// ����Ϊ����ֵ�����ͣ�idĬ��Ϊ ��������Ϊid
	@Bean
	public Person person() {
		return new Person("lisi",20);
	}
	
}
