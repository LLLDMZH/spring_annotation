package com.atguigu.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面类
 * @author lenovo
 *
 */

//告诉Spring当前类是一个切面类
@Aspect
public class LogAspects {
	
	// 抽取公共的切入点表达式
	
	//抽取公共的切入点表达式
	// 1.本类引用
	// 2、其他的切面引用 就是全名
	//@Pointcut("execution(* *..service.*.doSome(..))")

	@Pointcut("execution(public int com.atguigu.aop.MathCalculator.*(..))")
	public void pointCut() {};
	
	//@Before("public int com.atguigu.aop.MathCalculator.div(int, int)")
	//@Before("public int com.atguigu.aop.MathCalculator.*(..)")
	
	
	@Before("pointCut()")
	public void logStart(JoinPoint jp) {
		Object[] args = jp.getArgs();
		System.out.println(jp.getSignature().getName() + "运行。。参数列表是：{"+ Arrays.asList(args)+"}");
	}
	
	@After("execution(public int com.atguigu.aop.MathCalculator.*(..))")
	public void logEnd(JoinPoint jp) { //JoinPoint必须要出现在第一位
		System.out.println(jp.getSignature().getName() + "结束");
	}
	
	@AfterReturning(value = "com.atguigu.aop.LogAspects.pointCut()", returning = "results")
	public void logReturn(Object results) {
		System.out.println("除法正常返回。。运行结果是：{"+ results +"}");
	}
	
	@AfterThrowing(value = "pointCut()", throwing = "exception")
	public void logException(Exception exception) {
		System.out.println("除法异常。。异常信息：{"+ exception.getMessage() +"}");
	}
}
