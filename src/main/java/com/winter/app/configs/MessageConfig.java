package com.winter.app.configs;


import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//config 파일임을 명시함, 설정 class, XML역할을 함
@Configuration
public class MessageConfig implements WebMvcConfigurer{

	//bean 어노테이션: DI(dependency Injection 의존성 주입)
	//스프링으로 넘어오면서, 자바 클래스를 이용하여 빈을 생성한다. 이때 어노테이션을 사용해준다.
	//이 객체를 스프링 pool에 담아놨다가, 필요한 곳이 있을 때 꺼내서 사용해줌
	@Bean
	//method명은 꼭 localeResolver()로 설정한다.
	public LocaleResolver localeResolver() {
		//1. session을 이용
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		//기본 언어 설정
		resolver.setDefaultLocale(Locale.KOREAN);
		
		return resolver;
		
		//2. 쿠키 이용
//		CookieLocaleResolver cResolver = new CookieLocaleResolver();
//		cResolver.setDefaultLocale(Locale.KOREAN);
		//쿠키의 이름 설정, 쓰지 말 것을 권장
//		cResolver.setCookieName("lang");//deprecated
	}
	
	//Message Interceptor 객체 생성
	@Bean
	public LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		//parameter를 받아서 언어 구분
		//url?lang=en
		return changeInterceptor;
	}
}

