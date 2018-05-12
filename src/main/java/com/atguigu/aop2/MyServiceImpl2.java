package com.atguigu.aop2;

import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl2 implements MyService {

	@Override
	public void my() {
		System.out.println("my2");
	}

}
