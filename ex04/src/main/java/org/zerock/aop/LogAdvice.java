package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
@Aspect  //?
public class LogAdvice {

	//@AfterThrowing("execution(* org.zerock.service.SampleService*.*(..))")
	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		
		long start = System.nanoTime();
		
		log.info("Target: " + pjp.getTarget());
		log.info("Param: " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result = pjp.proceed();  //함수 호출 처리
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.nanoTime();
		
		log.info("TIME: " + (end-start));
		return result;
	}
}






