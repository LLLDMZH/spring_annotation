package com.atguigu.aop3;

import org.springframework.stereotype.Service;

public class MyServiceImpl1 implements MyService {

	@Override
	public int my() {
		System.out.println("my1");
		return 1;
	}

}
