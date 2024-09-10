package com.winter.app.lamdas;

public class Service {
	
	public void act() throws Exception{
		
		//서비스에서 minus를 쓰고 싶음 :: 기존 방식
		Minus minus = new Minus();
		minus.calc(1, 0);
		//어쨋든 이것도 mf 타입임. mf.calc() 이렇게 사용 가능
		
		//오버라이딩 필요 없이 바로 인터페이스 구현 해버리기
		Myfunction mf = (int num, int num2)->(num+num2);
		int result = mf.calc(3, 2);
		
		//곱셈을 오버라이딩으로 하지 않고 람다식으로 바로 구현해버릴 수 있음
		//단, 이걸 다른 곳에서 사용 못하고 여기에서만 사용할 수 있다.
		mf = (int n1, int n2) -> n1 * n2;
		result = mf.calc(3, 5);
		
		
	}

}
