package com.atguigu.bean;

// ����Import��ʽ���� û��ע�� �������ȫ����
public class Color {
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Color [car=" + car + "]";
	}
	
}
