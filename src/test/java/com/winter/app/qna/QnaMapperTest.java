package com.winter.app.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.util.Pager;

@SpringBootTest
class QnaMapperTest {
	
	@Autowired
	QnaMapper qnaMapper;
	
	@Test
	void getDetailTest() throws Exception{
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardNum(110L);
		qnaVO= qnaMapper.getDetail(qnaVO);
		
		assertNotNull(qnaVO);
	}
	
	@Test
	void addTest() throws Exception{
		//여러개의 글 추가헤주는 반복문
//		for(int i=4; i<110; i++) {
//			
//			QnaVO qnaVO = new QnaVO();
//			qnaVO.setBoardContents("c"+i);
//			qnaVO.setBoardTitle("t"+i);
//			qnaVO.setBoardWriter("y"+i);
//			qnaVO.setRef((long)i);
//			qnaVO.setStep(0L);
//			qnaVO.setDepth(0L);
//			int result = qnaMapper.add(qnaVO);
//			if(i%10 ==0) {
//				Thread.sleep(500);
//			}
//		}
//		
		//result가 1이길 희망합니다.
//		assertEquals(1, result);
		
	}

	@Test
	void getList() throws Exception {
		
		Pager pager = new Pager();
		pager.setPage(1L);
		pager.setKind("k1");
		pager.setSearch("2");
		pager.makeRow();
//		
//		pager.setStartRow(0L);
//		//perpage는 10이라고 설정해뒀으니까 안적음
		
		List<QnaVO> ar = qnaMapper.getList(pager);
		for(QnaVO qnaVO : ar) {
			System.out.println(qnaVO.toString());
		}
	}

}
