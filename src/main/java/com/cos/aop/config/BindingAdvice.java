package com.cos.aop.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cos.aop.domain.CommonDto;

@Component
@Aspect
public class BindingAdvice {
	
	private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);

	@Before("execution(* com.cos.aop.web..*Controller.*(..))")
	public void testCheck() {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		System.out.println("주소 :" + request.getRequestURI());
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
						log.warn(type+"."+method+"() -> 필드 :"+error.getDefaultMessage());
					}
					
					return new CommonDto<>(HttpStatus.BAD_REQUEST.value(),errmap);
				}
			}
		}
		return  proceedingJoinPoint.proceed();//마저 진행해라. 함수의 스택을 실행
	}
}
