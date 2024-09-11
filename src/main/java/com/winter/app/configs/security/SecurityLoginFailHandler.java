package com.winter.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.eclipse.jdt.internal.compiler.lookup.InferenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.winter.app.members.MemberMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{

	//파라미터가 request에 담겨져 있으니까, request.getparameter로 아이디를 꺼낼 수 있음
	//다섯번 입력 시 계정 잠김 이런거 할 수 있음.ㄴ
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.info("로그인 실패 시 추가적인 작업 메서드");
		
		String message = "로그인 실패";
		log.error("Exception : {}", exception);
		
		if(exception instanceof BadCredentialsException) {
			//비밀번호가 틀렸다는 의미
			message="비밀번호를 확인하세요.";
		}
		
		if(exception instanceof InternalAuthenticationServiceException) {
			//아이디가 틀렸다는 의미
			message="아이디를 확인하세요.";
		}
		
		if(exception instanceof AccountExpiredException) {
			//계정 유효기간이 만료되었다는 의미,isAccountNonExpired (VO에 있음)가 false인 경우
			message="계정 유효기간이 만료되었습니다.";
		}
		
		if(exception instanceof LockedException) {
			//계정 잠김, isAccountNonLocked()가 false인 경우
			message="잠긴 계정입니다.";
		}
		
		if(exception instanceof CredentialsExpiredException) {
			//비번 유효기간 만료, isCredentialsNonExpired() false인 경우
			message="비밀번호 유효기간이 만료되었습니다.";
		}
		
		if(exception instanceof DisabledException) {
			//유효하지 않은 사용자(탈퇴, 휴먼 등), isEnabled()false인 경우
			message="유효하지 않은 사용자입니다. 관리자에게 문의하세요.";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("/member/login?message=" + message);
		
	}
}
