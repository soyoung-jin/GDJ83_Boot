package com.winter.app.notice;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//test입니다를 알리는 어노테이션
@SpringBootTest
class NoticeMapperTest {
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Test
	void getListTest() throws Exception {
		List<NoticeVO> ar = noticeMapper.getList();
		assertNotEquals(0, ar.size());
		for(NoticeVO noticeVO: ar) {
			System.out.println(noticeVO.toString());
		}
	}
	
//	@Test
//	void getInsert() throws Exception{
//		List<NoticeVO> ar = noticeDAO.getList();
//		for(NoticeVO noticeVO: ar) {
//			System.out.println(noticeVO.toString());
//		}
//	}
}
