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
 * �Զ�װ�䣻
 * 		Spring��������ע�루DI������ɶ�IOC�����и��������������ϵ��ֵ
 * 1)��@Autowired:�Զ�ע�룺
 * 		1)��Ĭ�����Ȱ�������ȥ�������Ҷ�Ӧ�����: applicationContext.getBean(BookDao.class);
 * 		2)������ҵ������ͬ���͵�������ٽ����Ե�������Ϊ�����idȥ�����в���
 * 								applicationContext.getBean("bookDao")
 * 		3)��@Qualifier("bookDao"):ʹ��@Qualifierָ����Ҫװ��������id ������ʹ��������	
 * 		4)���Զ�װ��Ĭ��һ��Ҫ�����Ը�ֵ�ã�û�оͻᱨ��; ����require=false
 * 		5)��@Primary:��Spring �����Զ�װ���ʱ��Ĭ��ʹ����ѡ��Bean
 * 			Ҳ���Լ���ʹ��@Qualifierָ����Ҫװ���Bean
 * 		�ҵ��͸�ֵ
 * 		BookService {
 * 			@Autowired
 * 			BookDao bookDao;	
 * 		}
 * 
 * 2����Spring��֧��@Resource(JSR250)��@Inject(JSR330)[Java�淶ע��]
 * 		@Resource��
 * 				���Ժ�@Autowiredһ��ʵ���Զ�װ�书�ܣ�Ĭ���ǰ���������ƽ���װ��
 * 				û��֧��@Primary���ܣ� û��֧��@Autowired(required=false)
 * 		@Inject:
 * 			          ��Ҫ����javax.inject�İ���AutoWired @Primary��Ч��û��required=false�Ĺ���
 * 
 *      @Autowired:Spring����ģ�@Resource��@Inject����Java�淶
 * 		
 * 
 * AutowiredAnnotationBeanPostsProcessor:��������Զ�װ��Ĺ��ܡ�
 * 
 * 3)��@Autowired:�����������������������ԣ����Ǵ������л�ȡ���������ֵ
 * 		1)����ע�ڷ���֮��:@Bean + ���������������������л�ȡ��Ĭ�ϲ�д@AutowiredЧ����һ���ģ������Զ�װ��	
 * 		2)����ע�ڹ������ϣ�������ֻ��һ���вι�����������вι�������@Autowired����ʡ��
 * 		3)�����ڲ���λ��
 * 
 * 4)���Զ��������Ҫʹ��Spring�����ײ��һЩ���
 * 		(ApplicationContext��BeanFactory��xxx)
 * 		���ʵ��xxxAware���ɣ��ڴ��������ʱ�򣬻���ýӿڹ涨�ķ���ע��������
 * 		��Spring�ײ��һЩ���ע�뵽�Զ����Bean��
 * 		xxxAware������ʹ��xxxProcessor����
 * 
 * @author lenovo
 *
 */
@Configuration
@ComponentScan({"com.atguigu.service", "com.atguigu.controller",
		"com.atguigu.dao","com.atguigu.bean"})
public class MainConfigOfAutowired {
	
	// �ṩ����BookDao.class���͵�Bean
	
	@Primary
	@Bean("bookDao2")
	public BookDao bookDao() {
		BookDao bookDao = new BookDao();
		bookDao.setLable("2");
		return bookDao;
	}
	
	
	/**
	 * @Bean��ע�ķ������������ʱ�򣬷���������ֵ�������л�ȡ
	 * @return
	 */
	@Bean
	public Color color(Car car) {
		Color color = new Color();
		color.setCar(car);
		return color;
	}
	
}
