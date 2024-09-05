package com.winter.app.aops.pays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect //실행 설정 파일이라는 어노테이션
public class Card {

	@Around("execution(* com.winter.app.aops.transfers.Transfer.take*(..))")//언제 적용할건지의 시점 ,리턴타입은 *, take로 시작하는 모든 메서드, 매개변수는 없음 
	//운송수단 내리고 탈 때 카드를 찍는 행위
	//이 메서드가 advice
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable { //핵심로직(메서드)를 객체로 만들어서 넣어준 proceeding
		log.info("==== Before 카드 찍기 ====");
		
		log.info("=== ARGS ===: {}", joinPoint.getArgs());
		
		//이 사이에 bus와 subway 메서드가 들어와야함 >> proxy 개념
		Object obj = joinPoint.proceed(); //point-cut 진행시키라는 것
		
		log.info("==== return : {} ", obj);
		
		log.info("==== After 카드 찍기 ====");
		
		return obj;
	}
}
