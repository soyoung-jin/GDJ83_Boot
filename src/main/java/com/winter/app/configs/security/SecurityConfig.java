package com.winter.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


//설정파일임을 명시하는 어노테이션
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() throws Exception{
		return web -> web
						.ignoring()
						//웹의 기본 흐름이 모든 요청이 컨트롤러에서 들어온다
						//요청 중에서 우리가 일반적으로 쓰는 url도 있고, 이미지, css, js도 요청을 받아서 나간다.
						//security는 그걸 구분하지 않고 전부 검증하려 한다. 그래서 이를 무시하게끔 만들어주는 것
						//security에서 무시할 경로들을 다 적어준다.
						.requestMatchers("/imges/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vendor/**")
						.requestMatchers("favicon/**");
	}

	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
					//cors:
					.cors()
					.and()
					//csrf: 컨트롤러 입장에서 요청이 들어오면 url로 들어감. 컨트롤러는 이 url이 어디서 오는지 알 수 있을까? 없음.
					//insert할때 우리가 발행한 페이지인지 확인하기 위해서 비번,아이디를 확인해서 우리가 발행한 것이 아니면 막아줌
					.csrf()
					.disable();
		//권한에 관련된 설정
		//인터셉터 없이 하나의 페이지에 권한설정들을 모아서 처리할 수 있음
		httpSecurity.authorizeHttpRequests(
					(authorizeRequest)->
							authorizeRequest
										//루트 url이 오면, 누구나 다 들어가야 함 
										.requestMatchers("/").permitAll()
										.requestMatchers("/qna/list").permitAll()
										//로그인 된 사람만 들어오로록 설정
										.requestMatchers("/qna/*").authenticated()
										.requestMatchers("/notice/list","/notice/detail").permitAll()
										//db에서 ROLE_NAME이 ADMIN인 애들만 들어오도록 무조건 ROLE 어쩌고여야함
										.requestMatchers("/notice/*").hasRole("ADMIN")
										//권한이 manager도 되고, admin도 되고 둘중 하나 있으면 통과
										.requestMatchers("/manager/*").hasAnyRole("MANAGER","ADMIN")
										.requestMatchers("/member/add", "/member/login").permitAll()
										.requestMatchers("/member/*").authenticated()
										.anyRequest().permitAll()
										
					)//authorizeRequest 끝
		
					//formLogin 관련 설정 부분
					.formLogin(
							login ->
								login
									//로그인 폼을 우리가 만든 폼으로 쓰려는 것, 그 url를 적어준다.
									.loginPage("/member/login")
									//로그인 성공 시 어디로 보낼지 설정
									.defaultSuccessUrl("/")
									//로그인 실패 시
									.failureUrl("/member/login")
									//로그인 진행을 security가 하는데, login 진행하려면 id,pw 필요, 이 파라미터를 서버로 보냄
									//그래서 파라미터 이름을 정해준다. 기본이 username인데, 다른 이름(id)으로 파라미터 만들면, 명시해라 이거임
									//우리는 파라미터 이름을 username이라고 해서 주석처리
									//.usernameParameter("id")
									//기본명이 password가 아니고 'pw'로 사용한 경우
									//우리는 password로 해놔서 주석처리
									//.passwordParameter("pw")
									.permitAll()
							)
		
		;
		return httpSecurity.build();
	}
	
	//비밀번호 인코딩
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}

}
