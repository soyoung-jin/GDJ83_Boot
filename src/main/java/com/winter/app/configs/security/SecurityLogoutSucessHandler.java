package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.winter.app.members.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLogoutSucessHandler implements LogoutSuccessHandler {
	@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
		
		//로그아웃 성공하면 카카오 로그아웃을 진행하겠다는 의미
		//restTemplete deprecated되면 webClient로 사용하면 됨
		
		//AccessToken : 사용자의 AccessToken이 필요하다. 이건 어디서 가져오냐..
		//사용자
		MemberVO memberVO = (MemberVO) authentication.getPrincipal();
		log.info("===={}====", memberVO.getAccessToken());
		
		//이 핸들러는 카카오 로그인 사용자 뿐만 아니라 일반 사용자도 접근해서 로그아웃할 수있기 때문에, sns변수로 이를 구분해준다.
		//일반 로그인 유저를 위한 if문
		if(memberVO.getSns()==null) {
			response.sendRedirect("/");
			return;
		}
		//카카오 사용자를 위한 if문
		if(memberVO.getSns().equals("kakao")) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + memberVO.getAccessToken());
			//headers.setBearerAuth("Bearer " + memberVO.getAccessToken());

			HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(headers,null); 
			ResponseEntity<String> re = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/logout", req, String.class);
		
			log.info("logout id : {} ", re.getBody());
			
			response.sendRedirect("/");
			return;
		}
			
	}
}
