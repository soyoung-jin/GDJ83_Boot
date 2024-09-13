package com.winter.app.members;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MemberServiceTest {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Test
//	void test() throws Exception{
//		MemberVO memberVO = new MemberVO();
//		memberVO.setUsername("admin");
//		memberVO.setPassword(passwordEncoder.encode("admin"));
//		int result = memberMapper.pwUpdate(memberVO);
//		
//		assertEquals(1, result);
//	}
//	@Test
//	void test() throws Exception{
//		MemberVO memberVO = new MemberVO();
//		memberVO.setUsername("user");
//		memberVO.setPassword(passwordEncoder.encode("user"));
//		int result = memberMapper.pwUpdate(memberVO);
//		
//		assertEquals(1, result);
//	}
	
	//password가 일치하는 여부를 확인
	@Test
	void passwordUpdateTest() throws Exception{
		//기존 admin 비밀번호 -> 1234로 비밀번호 변경
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("admin");
		//기존 비밀번호 (인코딩 안하고 날것?으로 비교)
		memberVO.setPassword("admin");
		
		String newPassword = "1234";
		
		
		//detail 쿼리 조회하면 아이디랑 일치하는 유저 정보 불러올 수 있음, check에 admin관련 정보 가져옴
		MemberVO check = memberMapper.detail(memberVO);
		
		log.info("memberVO : {}",memberVO);
		log.info("check : {}",check);
		
		if(passwordEncoder.matches(memberVO.getPassword(),check.getPassword())) {
			log.info("패스워드 일치");
		} else {
			log.info("패스워드 불일치");
		}
		
		assertEquals(check.getPassword(), memberVO.getPassword());
	}

}
