package io.corp.calculator.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.corp.calculator.trace.TracerAPI;

@Aspect
@Order
@Component
public class PackageInterceptor  {
	
	@Autowired
	TracerAPI tracerAPI;
	
	@Around(value = "execution(* io.corp.calculator.controller.*.*(..))")
	public Object processDelegate(ProceedingJoinPoint joinPoint) throws java.lang.Throwable {


		Object proceed = null;

		try {
			proceed = joinPoint.proceed();

		} finally {
			tracerAPI.trace("finally");
		}

		tracerAPI.trace(proceed);

		return proceed;
	}



}