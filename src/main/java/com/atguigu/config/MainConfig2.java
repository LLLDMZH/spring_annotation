package com.atguigu.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import com.atguigu.bean.Color;
import com.atguigu.bean.ColorFactoryBean;
import com.atguigu.bean.Person;
import com.atguigu.bean.Red;
import com.atguigu.condition.LinuxCondition;
import com.atguigu.condition.MyImportBeanDefinitionRegistrar;
import com.atguigu.condition.MyImportSelector;
import com.atguigu.condition.WindowsCondition;

//�������ͳһ���� ���㵱ǰ��������������õ�����beanע��Ż���Ч
@Conditional({WindowsCondition.class})
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
// ���������idĬ���������ȫ����
public class MainConfig2 {
	
	/**
	 * Specifies the name of the scope to use for the annotated component/bean.
	 * <p>Defaults to an empty string ({@code ""}) which implies
	 * {@link ConfigurableBeanFactory#SCOPE_SINGLETON SCOPE_SINGLETON}.
	 * @since 4.2
	 * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE
	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION
	 * @see #value
	 * 
	 * prototype:��ʵ����ioc��������������ȥ���÷�������������������У�ÿ�λ�ȡ��ʱ��Ż���÷�����������
	 * singleton:��ʵ�� ��Ĭ��ֵ��:ioc������������÷��������������ioc�����С�
	 * 							�Ժ�ÿ�λ�ȡ����ֱ�Ӵ�����(map.get())����
	 * request:ͬһ�����󴴽�һ��ʵ��
	 * session:ͬһ��session����һ��ʵ��
	 * 
	 * �����أ�
	 * 	��ʵ��bean:Ĭ����������������ʱ�򴴽�����
	 *  @Lazy �����أ������������������󣬵�һ��ʹ�ã���ȡ��Bean�ڴ������󣬲����г�ʼ����
	 */
//	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy 
	@Bean("person")
	public Person person() {
		System.out.println("���������Person");
		return new Person("����",25);
	}
	
	/**
	 * @Conditional({}) :����һ�������������жϣ�����������������ע��bean
	 * ���ϵͳ��windows��������ע��("bill") 
	 * ���ϵͳ��linux��������ע��("linus")
	 */
	
	@Bean("bill")
	public Person person01() {
		return new Person("Bill Gates",62);
	}
	
	@Conditional({LinuxCondition.class})
	@Bean("linus")
	public Person person02() {
		return new Person("linus",48);
	}
	
	/**
	 * ��������ע�����
	 * 1.��ɨ��+�����עע��(@Controller @Service @Component @Repository)
	 * 2.@Bean�����������������������
	 * 3.@Import�����ٸ������е���һ�������
	 *     1)��@Import({Ҫ��������}),idĬ���������ȫ����
	 *     2)��ImportSelector:������Ҫ����������ȫ��������
	 *     3)��ImportBeanDefinitionRegistrar:
	 * 4.ʹ��Spring�����FactoryBean(����Bean)
	 *     1)��Ĭ�ϻ�ȡ�����ǹ���bean����getObject�����Ķ���
	 *     2)��Ҫ��ȡ����Bean��������Ҫ��idǰ���һ��&
	 *         ��ȡ����Bean���� �鿴BeanFactory���֪
	 */
	
	@Bean
	public ColorFactoryBean colorFactoryBean() {
		return new ColorFactoryBean();
	}
	
	
}
