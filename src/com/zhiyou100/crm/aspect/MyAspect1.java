package com.zhiyou100.crm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
// 切面类,定义了一些切面方法
public class MyAspect1 {

	// 前置通知[Before advice]：在连接点前面执行，前置通知不会影响连接点的执行，除非此处抛出异常。 
	public void before() {
		
		
		System.out.println("前置通知");
	}
	
	

	public void after() {
		
		System.out.println("最終通知通知");

		
	}
	

	public Object around(ProceedingJoinPoint point) {
		Object obj = null;
		System.out.println("环绕前通知");
	    Object[] objs =	point.getArgs();
	    System.out.println("被代理的对象:" + point.getTarget());
        System.out.println("代理对象自己:" + point.getThis());
        
	    for (int i = 0; i < objs.length; i++) {
			Object ob = objs[i];
			System.out.println(ob);
		}
		try {
			// 调用被拦截的方法
			obj = point.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("环绕后通知");

		return obj;

		
	}
	
	public void afterThrowing() {
		
		System.out.println("异常通知");

		
	}
	
	
	public void afterReturn() {
		
		System.out.println("正常返回通知");

		
	}
	
	
	
	
	
}
