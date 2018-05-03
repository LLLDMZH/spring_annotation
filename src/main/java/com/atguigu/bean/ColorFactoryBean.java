package com.atguigu.bean;

import org.springframework.beans.factory.FactoryBean;

// ������������ϵ�ʱ��FactoryBeanʹ�õ��ر��
// ����һ��Spring�����FactoryBean
public class ColorFactoryBean implements FactoryBean<Color>{

	// ����һ��Color��������������ӵ�������
	@Override
	public Color getObject() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ColorFactoryBean...getObect()");
		return new Color();
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Color.class;
	}

	// �ǵ�����
	// true:���bean�ǵ�ʵ�����������б���һ��
	// false:��ʵ����ÿ�λ�ȡ����һ���µ�
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
