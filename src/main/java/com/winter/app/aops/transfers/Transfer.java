package com.winter.app.aops.transfers;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Transfer {
	
	//버스를 타는 행위
	public String takeBus(int num) {
		log.info("==== 버스 타기 ====");
		
		return "BUS";
	}
	
	//지하철을 타는 행위
	public void takeSubway(Long m, String name) {
		log.info("==== 지하철 타기 ====");
	}
	
	//걸어가는 행위
	public void walk() {
		log.info("==== 걸어가기 ====");
	}

}
