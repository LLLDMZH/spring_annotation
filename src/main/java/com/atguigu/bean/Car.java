package com.atguigu.bean;

public class Car {

	public Car() {
		System.out.println("Car Constructor");
	}
	
	
	public void init() {
		
		// ����Դ��ʼ����ʱ�� �ܶ�����Ը�ֵ
		System.out.println("Car init");
	}
	
	public void destroy() {
		// ���ٵ�ʱ��ҲҪ�ر�����Դ������
		System.out.println("Car destroy");
	}
}
