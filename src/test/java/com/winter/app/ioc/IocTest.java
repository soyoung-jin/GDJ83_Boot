package com.winter.app.ioc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.robot.Robot;

//클래스 앞에 public 생략되어 있음. default 접근제어자로 작동 중
@SpringBootTest
class IocTest {
	
	//Robot클래스의 객체를 만들어서 사용하려 함
	@Autowired
	private Robot robot;

	@Test
	void test() {
		
		System.out.println("test case");
		robot.getRobotArm().punch();
		assertNotNull(robot);
		
		
		System.out.println("test case");
		//같으니까 무조건 통과됨
		assertEquals(1, 1);
	}

}
