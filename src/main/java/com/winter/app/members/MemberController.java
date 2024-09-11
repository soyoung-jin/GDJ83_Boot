package com.winter.app.members;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.winter.app.validate.MemberAddGroup;
import com.winter.app.validate.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService; 
	
	//회원가입
	@GetMapping
	//매개변수 QnaVO 앞에는 @modelAttribute가 생략되어 있다.
	//model의 값은 QnaVO , 속성명은 클래스명의 앞글자를 소문자로 바꾼 것으로 설정된다(별도 설정 없으면)
	public void add(MemberVO memberVO) throws Exception{
		//model.addAttribute("memberVO", new MemberVO());
	}
	
	@PostMapping("add") 
	public String add(@Validated({MemberAddGroup.class}) MemberVO memberVO, BindingResult bindingResult) throws Exception{
		//비밀번호가 일치하지 않을 시
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		//에러가 발생하면 member/add 페이지로 다시 이동하도록 설정
		if(bindingResult.hasErrors()) {
			return "member/add";
		}
		
		if(check) {
			return "member/add";
		}
		
		int result = memberService.add(memberVO);
		
		//if문으로 등록 성공 실패 잡아주면 됨
		return "redirect:../";
	}

	//detail(login)
	@GetMapping("login")
	public String login(String message, Model model) throws Exception{
		//로그인 실패 시, 메세지 띄워줌
		model.addAttribute("message", message);
		
		//로그인 된 상태인지 아닌지 구분, 뒤로가기 눌러도 다시 로그인창으로 가지 않게 해줌
		SecurityContext context =SecurityContextHolder.getContext();
		log.info("context::: {}",context);
		//null - 로그인 안함 - 로그인페이지로 가라
		if(context == null) {
			return "member/login";
		}
		
		String user =context.getAuthentication().getPrincipal().toString();
		log.info("user::: {}",user);

		if(user.equals("anonymousUser")) {
			return "member/login";
		}
		
		return "redirect:/";
	}
//	security에서 처리해서 주석처리	
//	@PostMapping("login")
//	public String login(MemberVO memberVO, HttpSession httpSession) throws Exception{
//		memberVO = memberService.detail(memberVO);
//		//if문으로 로그인 성공 실패 잡아주면 됨
//		
//		if(memberVO != null) {
//			httpSession.setAttribute("member", memberVO);
//		}
//		
//		return "redirect:../";
//	}
//	
	//logout
	@GetMapping("logout")
	public String logout(HttpSession httpSession) throws Exception{
		httpSession.invalidate();
		
		return "redirect:../";
	}
	
	//마이페이지
	@GetMapping("mypage")
	public void mypage(HttpSession session) throws Exception{
		//1. 세션 사용
		//속성명들을 꺼내준다. 속성명이 몇개 들어있는지 모르면 while문 돌린다.
		Enumeration<String> en = session.getAttributeNames();
	
		while(en.hasMoreElements()) {
			String name = en.nextElement();
			log.info("name:: {} ", name); //결과로  name:: SPRING_SECURITY_CONTEXT 나옴
		}
		
		//어떤 타입일지 모르니까 object로 받음
//		Object object = session.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContext sc =  (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
//		log.info("object:: {}" , object.getClass()); //object:: class org.springframework.security.core.context.SecurityContextImpls
		//즉. SecurityContext를 구현함. 위에 형변환 해줌
		log.info("sc::{}", sc);
		
		//2. SecurityContextHolder 사용
		SecurityContext context = SecurityContextHolder.getContext();
		log.info("context : {}", context); //SecurityContext sc 로그랑 똑같은 결과가 나옴
		
	
		Authentication authentication =context.getAuthentication();
		log.info("autehntication >: {}", authentication);
		
		//사용자 정보가 담긴 principal
		//principal의 타입이 memberVO라고 위에 로그에서 찍혔음!
		MemberVO memberVO = (MemberVO) authentication.getPrincipal();
		log.info("memberVO: {}", memberVO);
		log.info("Name: {}", authentication.getName());
		
	}
	
	//마이페이지 수정
	@GetMapping("update")
	public String update(HttpSession httpSession, Model model) throws Exception{
		//add.jsp form태그에 modelAttribute를 보내주기로 되어있기 때문에 매개변수에 memberVO를 넣어주어야 한다.
		//매개변수로 member변수를 넣어주면 새로 만들어서 넣어주기 때문에 기존에 member를 사용할 수 없다.
		//session에서 memberVO를 꺼내서 담아서 보내주어야 한다.
		MemberVO memberVO = (MemberVO)httpSession.getAttribute("member");
		model.addAttribute("memberVO", memberVO);
		
		return "member/update";
	}
	
	//회원 수정할때도 검증이 필요함
	//단, 비밀번호는 검증하고 싶지 않음. 검증에서 제외해야 함, 그래서 validated 사용
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		
		return "redirect:./mypage";
	}
	
}
