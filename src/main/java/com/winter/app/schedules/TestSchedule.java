package com.winter.app.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.qna.QnaMapper;
import com.winter.app.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	// initialDelay: 서버가 시작하고 딜레이를 주어라, 2초 기다렸다가 실행해라 의미
	//메서드 실행 후 종료까지 약 2초가 걸린다. test1이라는 메서드는 1초 간격으로 실행된다.
	//해당 메서드가 종료되고 나서 1초 간격이다. inialDelay로 2초를 주어서 2초 되고 난 다음에 1초를 기다리고 다시 2초 실행되고 다시 1초 기다리고, 반복
	//@Scheduled(fixedDelayString = "1000", initialDelay = 2000)
	public void test1() throws Exception{
		//반복적인 일을 할 내용을 적는다.
		
		log.error("Schedule Test");
	}
	
	//@Scheduled(fixedRate = 2000, initialDelayString = "3000")
	//메서드가 언제 끝날 지 상관없이 바로바로 시작한다.
	//무조건 고정 간격으로 실행해버린다.
	public void test2() throws Exception{
		log.error("============Schedule Test===============");
	}
	
	//매시 49분 0초에 무언가를 하겠다는 의미, 노션 참고
	//매 5초마다 실행하도록 설정
	//@Scheduled(cron = "*/5 * * * * *")
	public void test3() throws Exception{
		log.error("============Schedule Test===============");
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardWriter("test");
		qnaVO.setBoardTitle("Title");
		qnaVO.setBoardContents("Contents");
		qnaMapper.add(qnaVO);
		qnaMapper.refUpdate(qnaVO);
	}
	
	

}
