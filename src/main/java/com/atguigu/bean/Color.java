package com.atguigu.bean;

// 利用Import方式导入 没有注解 导入的是全类名
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
