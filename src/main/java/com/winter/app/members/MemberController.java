package com.winter.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService; 
	
	//add
	@GetMapping("add")
	public void add() throws Exception{
		
	}
	
	@PostMapping("add")
	public String add(MemberVO memberVO) throws Exception{
		int result = memberService.add(memberVO);
		
		//if문으로 등록 성공 실패 잡아주면 됨
		return "redirect:../";
	}
	
	//detail(login)
	@GetMapping("login")
	public void login() throws Exception{
		
	}
	
	@PostMapping("login")
	public String login(MemberVO memberVO, HttpSession httpSession) throws Exception{
		memberVO = memberService.detail(memberVO);
		//if문으로 로그인 성공 실패 잡아주면 됨
		
		if(memberVO != null) {
			httpSession.setAttribute("member", memberVO);
		}
		
		return "redirect:../";
	}
	
	//logout
	@GetMapping("logout")
	public String logout(HttpSession httpSession) throws Exception{
		httpSession.invalidate();
		
		return "redirect:../";
	}
}
