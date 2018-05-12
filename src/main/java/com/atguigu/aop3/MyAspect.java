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
		System.out.println("前置通知");
		System.out.println(jp.getSignature().toString());
	}
	
	public void afterReturing(Object result) {
		System.out.println("后置通知");
		System.out.println(result);
	}
	public void after() {
		System.out.println("最终通知");
	}
	
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("环绕");
		pjp.proceed();
		System.out.println("环绕结束");
	}
}
