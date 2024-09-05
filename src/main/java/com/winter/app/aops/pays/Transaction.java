package com.winter.app.aops.pays;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
//보고 있다가 둘 중 하나가 exception 발생 시 rollback 시켜주는 역할의 클래스
public class Transaction {
	
	//롤백시켜주는 애들 누구로 할건지 결정함
	@AfterThrowing("execution(* com.winter.app.*.*.set*(..))")
	public void rollBack() {
		
	}
}
