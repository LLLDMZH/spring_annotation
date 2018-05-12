package com.atguigu.aop3;

import org.springframework.stereotype.Service;

public class MyServiceImpl2 implements MyService {

	@Override
	public int my() {
		System.out.println("my2");
		return 2;
	}

}
