package com.winter.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	//web-inf/views/index.jsp를 찾아감
	@GetMapping("/")
	public String home() throws Exception{
		log.trace("trace"); //추적, 세밀하게 로그를 찍고 싶을 때
		log.debug("Debug"); //검사, 추적보다 덜 세밀
		log.info("info"); //일반적 정보 , 기본설정이 info부터 밑으로 찍게 되어 있음 그래서 trace, trace 안나옴
		log.warn("warn"); //문제가 있을 경우에 경고 로그 찍어줌
		log.error("error"); //에러가 발생했을 경우 로그 찍어줌
		
		return "index";
	}
	
	
 
}
