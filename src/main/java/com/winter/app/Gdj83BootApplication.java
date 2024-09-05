package com.winter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy //생략 가능
public class Gdj83BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gdj83BootApplication.class, args);
	}

}
