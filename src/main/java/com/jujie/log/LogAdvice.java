package com.jujie.log;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.springframework.aop.AfterReturningAdvice;

public class LogAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("系统日志："+(new Date())+":"+"调用了"+method.getName()+" :使用了参数"+(Arrays.toString(args)));
	}

}
