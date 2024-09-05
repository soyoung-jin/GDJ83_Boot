package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//설정 클래스라는 의미의 어노테이션, xml느낌
@Configuration

//webMVCConfigure 구현 - 웹 설정에 관련한 인터페이스 오버라이딩 해야할 메서드가 있음
public class FileConfig implements WebMvcConfigurer {
	//file라고 시작하는 url이 오면 D:upload(file)를 찾아가게끔 함
	
	//이 두개의 변수를 아래 핸들러랑 매핑시켜주려고 함
	@Value("${app.url.path}") //app.url.path=/files/**
	private String url;
	
	@Value("${app.upload.location}")//app.upload.location=file:///D:/upload/
	private String file;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//등록한다. 이런 url이 들어왔을 때 file에서 찾아서 자원을 등록한다.
		//<resources mapping=”/files/**" location="D:/upload/" /> 이전 코드로 하면 이 의미
		registry.addResourceHandler(url)
				.addResourceLocations(file);
	}
	

}
