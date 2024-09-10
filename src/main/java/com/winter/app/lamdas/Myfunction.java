package com.winter.app.lamdas;

@FunctionalInterface
public interface Myfunction {
	
	public int calc(int num1, int num2) throws Exception;
	
	//public int calc2() --> 이렇게 메서드를 사나 더 만들면 에러가 뜸, 구현이 안되기 때문에
	//이런 실수를 예방하기 위해 어노테이션 사용

}
