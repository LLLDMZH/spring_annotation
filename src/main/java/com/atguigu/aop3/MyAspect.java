package com.atguigu.aop3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

public class MyAspect {
	
	public void joinPoint() {}
	
	public void Before(JoinPoint jp) {
		System.out.println("ǰ��֪ͨ");
		System.out.println(jp.getSignature().toString());
	}
	
	public void afterReturing(Object result) {
		System.out.println("����֪ͨ");
		System.out.println(result);
	}
	public void after() {
		System.out.println("����֪ͨ");
	}
	
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("����");
		pjp.proceed();
		System.out.println("���ƽ���");
	}
}
