package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.winter.app.home.interceptors.AdminCheckInterceptor;
import com.winter.app.home.interceptors.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	//어떤 인터셉터를 실행할건지는 얘로 결정됨
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	
	//message 관련 객체
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
	//인터셉터 구현 오버라이딩
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//어떤 url이 왔을 때 어떤 interceptor를 실행할 것인가를 설정해준다.
		//qna의 list를 로그인 한 사용자만 보게 하고 싶게 함 >> /qna/list url 오면 LoginInterceptor를 거치게 하자는 것
		
		//인터셉트 등록
		//registry.addInterceptor(loginInterceptor)
				//어떤 url이 왔을 때
//				addPathPatterns("/qna/list")
				//qna로 시작하는 모든 것들은 인터셉터 거쳐라
				//.addPathPatterns("/qna/*");
				//qna/list는 로그인 안해도 볼수있다.
//				.excludePathPatterns("/qna/list");
		
		//로그인 한 애들 중에 관리자 권한이 있는 애들만 통과시키자.
		registry.addInterceptor(adminCheckInterceptor)
				.addPathPatterns("/admin/*");
		
		//method 체이닝
		//적용할 message Interceptor 등록
		registry.addInterceptor(localeChangeInterceptor)
				.addPathPatterns("/**");

	}
}
