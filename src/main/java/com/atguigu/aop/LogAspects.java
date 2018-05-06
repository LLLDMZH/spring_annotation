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
 * ������
 * @author lenovo
 *
 */

//����Spring��ǰ����һ��������
@Aspect
public class LogAspects {
	
	// ��ȡ�������������ʽ
	
	//��ȡ�������������ʽ
	// 1.��������
	// 2���������������� ����ȫ��
	//@Pointcut("execution(* *..service.*.doSome(..))")

	@Pointcut("execution(public int com.atguigu.aop.MathCalculator.*(..))")
	public void pointCut() {};
	
	//@Before("public int com.atguigu.aop.MathCalculator.div(int, int)")
	//@Before("public int com.atguigu.aop.MathCalculator.*(..)")
	
	
	@Before("pointCut()")
	public void logStart(JoinPoint jp) {
		Object[] args = jp.getArgs();
		System.out.println(jp.getSignature().getName() + "���С��������б��ǣ�{"+ Arrays.asList(args)+"}");
	}
	
	@After("execution(public int com.atguigu.aop.MathCalculator.*(..))")
	public void logEnd(JoinPoint jp) { //JoinPoint����Ҫ�����ڵ�һλ
		System.out.println(jp.getSignature().getName() + "����");
	}
	
	@AfterReturning(value = "com.atguigu.aop.LogAspects.pointCut()", returning = "results")
	public void logReturn(Object results) {
		System.out.println("�����������ء������н���ǣ�{"+ results +"}");
	}
	
	@AfterThrowing(value = "pointCut()", throwing = "exception")
	public void logException(Exception exception) {
		System.out.println("�����쳣�����쳣��Ϣ��{"+ exception.getMessage() +"}");
	}
}
