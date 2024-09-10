package com.winter.app.lamdas;

public class Minus implements Myfunction{

	@Override
	public int calc(int num1, int num2) throws Exception {
		int result = num1 - num2;
		
		return result;
	}
}
