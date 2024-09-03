package com.winter.app.qna;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class QnaControllerTest {

//	@Autowired
//	private WebApplicationContext ctx;
	@Autowired
	private MockMvc mockMvc;
	
//	@Test
//	void test() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//		
//	}
	
	@Test
	void getListTest() throws Exception{
		
		//map은 키:밸류에서 키의 중복을 허용하지 않는다.
		//만약 a:1이 있는데, a:2 하면 a키가 수정되어 2가 값으로 들어간다.
		//MultiValueMap는 중복 키를 허용한다.
		//a:1, a:2라는 키밸류가 있으면, 같은 a라는 키로 값이 1,2가 들어가게 된다.
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("page", "1");
		map.add("kind", "k1");
		map.add("search", "2");
	
		//이거는 에러 발생
//		Map<String, String> map2 = new HashMap<>();
//		map2.put("page", "1");
//		map2.put("kind", "k1");
//		map2.put("search", "2");
		
		
		mockMvc.perform(
						get("/qna/list")
						.params(map)
//						.param("page", "1")
//						.param("kind", "k1")
//						.param("search", "2")
						).andDo(print());
	}
	
	@Test
	public void getDetaiTest() throws Exception {
		
		mockMvc.perform
				//요청
				(
				//get방식이라서 get
				get("/qna/detail?boardNum=110")
//				get("/qna/list?page=1")
				//파라미러란, 컨트롤러가 받을 때 QnaVO로 받는 것이고, 파라미터는 키,밸류로 구성 키는 boardNum, 값은 110
//				.param("boardNum", "110")
				)
				//응답
				.andExpect(status().isOk())
				.andDo(print())
		;
	}
	
	@Test
	public void getAddTest() throws Exception{
		
	}
}
