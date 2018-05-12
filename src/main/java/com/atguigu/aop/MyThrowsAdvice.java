package com.atguigu.aop;

import org.springframework.aop.ThrowsAdvice;

// �쳣֪ͨ
public class MyThrowsAdvice implements ThrowsAdvice{
	/*
	 * <pre class="code">public void afterThrowing(Exception ex)</pre> <pre
	 * class="code">public void afterThrowing(RemoteException)</pre> <pre
	 * class="code">public void afterThrowing(Method method, Object[] args, Object
	 * target, Exception ex)</pre> <pre class="code">public void
	 * afterThrowing(Method method, Object[] args, Object target, ServletException
	 * ex)</pre>
	 *
	 */
	
	public void afterThrowing(Exception ex) {
		System.out.println(ex.getMessage());
	}
}
