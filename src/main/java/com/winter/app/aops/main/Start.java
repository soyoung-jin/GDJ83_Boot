package com.winter.app.aops.main;

import org.eclipse.tags.shaded.org.apache.xalan.trace.TraceListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winter.app.aops.pays.Card;
import com.winter.app.aops.transfers.Transfer;

@Component
public class Start {

	//운송수단
	@Autowired
	private Transfer transfer;
	
	//카드찍기
	@Autowired
	private Card card;
	
	//집에서 학원까지 오는 여정에 운송수단을 호출하려 함
	public void go() {
		//운송수단(버스,지하철) 타기 전에 그냥 탈 수 없으니까 카드를 찍어야 함(내릴때, 탈때)
		//카드 찍는 로직을 작성해야함--> 반복적 --> 클래스로 만들고 메서드로 집어넣고 사용
		//탈 때 카드를 찍는다.
//		card.cardCheck();
		
		//집에 나와서 버스를 탄다.
		transfer.takeBus(50);
		//내릴때 카드를 찍는다.
//		card.cardCheck();
		
		//지하철 타기전에 카드 찍는다.
//		card.cardCheck();
		//버스에서 내려서 지하철을 탄다.
		transfer.takeSubway(15L, "2호선");
		//내릴 떄 카드 찍는다.
//		card.cardCheck();
		
		//걷는다.
		transfer.walk();
		
		//이렇게 반복되는 코드가 또 생기게 됨 --> 한계 --> AOP 개념 탄생
	}
}
