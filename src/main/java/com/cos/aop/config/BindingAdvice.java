package com.cos.aop.config;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.aop.domain.CommonDto;

@Component
@Aspect
public class BindingAdvice {
	
	@Before("execution(* com.cos.aop.web..*Controller.*(..))")
	public void testCheck() {
		System.out.println("전처리 로그를 남겼습니다.");
	}
	
	@After("execution(* com.cos.aop.web..*Controller.*(..))")
	public void testCheck2() {
		System.out.println("후처리 로그를 남겼습니다.");
	}
	
	@Around("execution(* com.cos.aop.web..*Controller.*(..))")
	public Object validcheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();
		System.out.println("type : " + type);
		System.out.println("method : " + method);
		
		Object[] args = proceedingJoinPoint.getArgs();	
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				if(bindingResult.hasErrors()) {
					Map<String,String>errmap = new HashMap<>();
					for(FieldError error : bindingResult.getFieldErrors()) {
						errmap.put(error.getField(), error.getDefaultMessage());
					}
					
					return new CommonDto<>(HttpStatus.BAD_REQUEST.value(),errmap);
				}
			}
		}
		return  proceedingJoinPoint.proceed();//마저 진행해라. 함수의 스택을 실행
	}
}
