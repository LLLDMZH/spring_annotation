package com.atguigu.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
	
	@Pointcut("execution(* com.atguigu.aop2.MyService.my())")
	public void joinPoint() {}
	
	// 两个都切
	@Before(value = "joinPoint()")
	public void Before(JoinPoint jp) {
		System.out.println("前置通知");
		System.out.println(jp.getSignature().toString());
	}
	
	@AfterReturning(value = "execution(* *..aop2.MyServiceImpl2.my())", returning = "result")
	public void afterReturing(Object result) {
		System.out.println("后置通知");
		System.out.println(result);
	}
	@After("execution(* *..aop2.MyServiceImpl1.my())")
	public void after() {
		System.out.println("最终通知");
	}
	
	@Around("execution(* *..aop2.MyService.my())")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("环绕");
		pjp.proceed();
		System.out.println("环绕结束");
	}
}
