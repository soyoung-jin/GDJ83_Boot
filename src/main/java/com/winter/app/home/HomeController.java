package com.winter.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//web-inf/views/index.jsp를 찾아감
	@GetMapping("/")
	public String home() throws Exception{
		return "index";
	}
	
	
 
}
