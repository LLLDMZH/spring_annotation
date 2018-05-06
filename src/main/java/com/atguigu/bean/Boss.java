package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Ĭ�ϼ���ioc�����е��������������������޲ι��������������ٽ��г�ʼ����ֵ�Ȳ���
@Component
public class Boss {
	
	// @Autowired
	private Car car;
	
	
	public Car getCar() {
		return car;
	}

	
	// ������Ҫ�õ���������Ǵ������л�ȡ
	// ���ֻ��һ�������� ��ô����Boss����������
//	@Autowired
//	public Boss(Car car) {
//		super();
//		this.car = car;
//		System.out.println("Boss ���вι�����");
//	}
//	
	
	
	public Boss(@Autowired Car car) {
		super();
		this.car = car;
		System.out.println("Boss ���вι�����");
	}

	//	@Autowired 
	// ��ע�ڷ�����Spring����������ǰ���󣬾ͻ���÷�������ɸ�ֵ��
	// ����ʹ�õĲ������Զ������͵�ֵ��ioc�����л�ȡ
	public void setCar(Car car) {
		this.car = car;
	}


	@Override
	public String toString() {
		return "Boss [car=" + car + "]";
	}
	
}
