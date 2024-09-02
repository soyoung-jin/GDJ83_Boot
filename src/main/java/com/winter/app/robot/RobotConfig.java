package com.winter.app.robot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//xml대신에 java를 이용해서 객체 생성
//자바에서 new생성자로 객체 만들었듯이 그렇게 다시 사용

//스프링이 읽을 수 있도록 어노테이션 추가(xml 역할을 하는 어노테이션)
@Configuration
public class RobotConfig {
	
	//이것이 바로 <bean class=""> 이렇게 썼던 역할을 대신해주는 것이다.
	//이때, 그냥 이렇게 하면 스프링 컨테이너가 아래 메서드가 있는지 알수 없음. 그래서 아래 메서드를 실행하라는 어노테이션 @bean 줘야 함. 그러면 객체 만들어줌
	//() 괄호 안은 bean의 이름을 지정해준 것이다.
	//객체를 만들면 빈의 이름 지정해야함. 빈의 이름을 따로 지정하지 않으면 클래스명의 첫글자가 소문자가 됨.
	
	@Bean("ro") 
	RobotArm makeArm() {
		return new RobotArm();
	}
	
	//이런 방식도 가능
	@Bean
	Robot makeRobot() {
		Robot robot = new Robot();
//		robot.setRobotArm(new RobotArm()); 이렇게 해도 되고
		robot.setRobotArm(makeArm()); //이렇게 메서드를 호출해서 robotArm의 객체를 생성해도 됨
		
		return robot;
	}
	
}
