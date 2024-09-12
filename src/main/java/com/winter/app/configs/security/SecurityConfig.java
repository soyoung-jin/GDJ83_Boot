package com.winter.app.configs.security;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.winter.app.members.MemberUserService;


//설정파일임을 명시하는 어노테이션
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecurityLoginSuccessHandler securityLoginSuccessHandler; 
	
	@Autowired
	private SecurityLoginFailHandler securityLoginFailHandler;
	
	@Autowired
	private MemberUserService memberUserService;
	
	
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
		//로그인 실패시 메세지 인코딩
		String message = URLEncoder.encode("로그인 실패", "UTF-8");
		httpSecurity
					//cors:
					.cors()
					.and()
					//csrf: 컨트롤러 입장에서 요청이 들어오면 url로 들어감. 컨트롤러는 이 url이 어디서 오는지 알 수 있을까? 없음.
					//insert할때 우리 가 발행한 페이지인지 확인하기 위해서 비번,아이디를 확인해서 우리가 발행한 것이 아니면 막아줌
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
									//로그인 폼을 개발자가 만든 폼으로 쓰려는 것, 그 url를 적어준다.
									.loginPage("/member/login")
									//로그인 성공 시 어디로 보낼지 설정
									//.defaultSuccessUrl("/")
									//추가적인 작업 하고 싶을 때
									.successHandler(securityLoginSuccessHandler)
									
									//로그인 실패 시 메세지 둘중 하나 사용
									//.failureUrl("/member/login?message=" + message)
									.failureHandler(securityLoginFailHandler)
									
									//로그인 진행을 security가 하는데, login 진행하려면 id,pw 필요, 이 파라미터를 서버로 보냄
									//그래서 파라미터 이름을 정해준다. 기본이 username인데, 다른 이름(id)으로 파라미터 만들면, 명시해라 이거임
									//우리는 파라미터 이름을 username이라고 해서 주석처리
									//.usernameParameter("id")
									//기본명이 password가 아니고 'pw'로 사용한 경우
									//우리는 password로 해놔서 주석처리
									//.passwordParameter("pw")
									.permitAll()
							)
					//로그아웃 관련 설정
					.logout(
							logout ->
								logout
									//로그아웃은 자동으로 되는데, 로그아웃 시 어디로 갈지 정해주자
									.logoutUrl("/member/logout") //= RequestMatcher("url")
									//얘도 로그아웃 url 경로 지정, 둘 중 하나 쓰면 된다.
									//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
									.logoutSuccessHandler(null)
									//로그아웃 성공 시 어디로 갈건지
									.logoutSuccessUrl("/")
									.invalidateHttpSession(true)
									//.deleteCookies(null) 쿠키 삭제하고 싶을 때, 쿠키의 이름을 괄호안에 적어주면 됨
							)
					//rememberMe
					.rememberMe(
							remember ->
								remember
									//파라미터 이름을 적어줌(login input 태그에 있음)
									.rememberMeParameter("rememberMe")
									//토큰의 유효시간
									.tokenValiditySeconds(60)
									//개발자 마음대로 키 세팅, token 생성 시 사용되는 값, 필수값
									.key("rememberme")
									//로그인 시, 인증절차(로그인)를 진행할 UserDetailService(컨트롤러로 가는게 아니라 얘를 받음, 로그인을 진행해줌)
									.userDetailsService(memberUserService)
									//로그인이 성공할 경우 진행할 핸들러
									.authenticationSuccessHandler(securityLoginSuccessHandler)
									//로그인이 실패할 경우 진행할 핸들러
									.useSecureCookie(false)
								)
					//동시 세션(접속) 설정
					.sessionManagement(
							sessionManager ->
								sessionManager
									//최대 몇개 세션 하용? 0이면 없는거 1이면 한개, -1이면 무제한
									.maximumSessions(1)
									//기존 사용자를 인증 실패 할거냐 아니면 로그인 시도자를 실패하게 할거냐? false: 이전(기존)사용자, true:새로운 사용자
									.maxSessionsPreventsLogin(false)
									//세션 만료 시 이동할 페이지
									.expiredUrl("/member/login")
									//세션 고정..하려했는데 없어짐
									
									)
					//소셜 로그인 항목
					.oauth2Login(
							oauth2 ->
								oauth2
									.userInfoEndpoint(
											user ->
												user.userService(memberUserService)
											)
									
							)

		;
		return httpSecurity.build();
	}
	
	//비밀번호 인코딩
	//평문을 암호화 시켜주는 메서드
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
