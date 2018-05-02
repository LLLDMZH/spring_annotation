package com.atguigu.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

// �ж��Ƿ�ΪLinuxϵͳ
public class LinuxCondition implements Condition{

	/**
	 * ConditionContext:�ж�������ʹ�õ������Ļ���
	 * AnnotatedTypeMetadata��ע����Ϣ
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// �Ƿ�ΪLinuxϵͳ
		// 1.��ȡiocʹ�õ�beanFactory
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		// 2.��ȡ�������
		ClassLoader classLoader = context.getClassLoader();
		// 3.��ȡ��ǰ������Ϣ
		Environment environment = context.getEnvironment();
		// 4.��ȡbean�����ע����
		BeanDefinitionRegistry registry = context.getRegistry();
		
		// ����ͨ�����������-Dos.name=Linux����
		String property = environment.getProperty("os.name");
		// �����ж�������beanע�������Ҳ���Ը�������ע��bean
		boolean definition = registry.containsBeanDefinition("person");
		if (property.contains("Linux")) {
			return true;
		}
		return false;
	}

}
