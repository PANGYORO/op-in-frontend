package com.c211.opinbackend.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogAop {
	@Before("execution(* com.c211.opinbackend..*(..)) && !execution(* com.c211.opinbackend.config..*(..))"
		+ "&& !execution(* com.c211.opinbackend.auth.jwt..*(..))"
		+ " && !execution(* com.c211.opinbackend.batch..*(..))")
	public void before(JoinPoint joinPoint) {
		log.info("[log] {} -> {} 실행", joinPoint.getSignature().getDeclaringType().getSimpleName(),
			joinPoint.getSignature().getName());
	}

	@After("execution(* com.c211.opinbackend..*(..)) && !execution(* com.c211.opinbackend.config..*(..))"
		+ "&& !execution(* com.c211.opinbackend.auth.jwt..*(..))"
		+ " && !execution(* com.c211.opinbackend.batch..*(..))")
	public void after(JoinPoint joinPoint) {
		log.info("[log] {} -> {} 종료", joinPoint.getSignature().getDeclaringType().getSimpleName(),
			joinPoint.getSignature().getName());
	}

	@AfterThrowing(value =
		"execution(* com.c211.opinbackend..*(..)) && !execution(* com.c211.opinbackend.config..*(..))"
			+ "&& !execution(* com.c211.opinbackend.auth.jwt..*(..))", throwing = "exception")
	public void writeFailLog(JoinPoint joinPoint, Exception exception) throws RuntimeException {
		//logging
		//exception 으로 해당 메서드에서 발생한 예외가져오기 가능
		exception.printStackTrace();
	}

}
