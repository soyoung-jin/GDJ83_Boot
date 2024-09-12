package com.winter.app.webClient;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.cfg.CoercionConfig;
import com.winter.app.comments.PostVO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@SpringBootTest
class ClientTest {
	
	@Test
	void webClientTest() {
		//요청 객체 생성 
		WebClient webClient = WebClient .builder()//설정
										.baseUrl("https://jsonplaceholder.typicode.com/")
										.build();
		
		Flux<PostVO> res = webClient.get()
										.uri("posts")
										//추가적인 헤더, 쿠키 넣어도 됨
										
										//여기서부턴 응답
										.retrieve()
										//한개일때 mobo, 여러개는 flux
										.bodyToFlux(PostVO.class);
			
		//요거는 안되나봄
		res.subscribe(v->{
			//v에 super PostVO 타입이 들어옴
			log.info("v: {}",v);
		});
//		log.info("webClient Result : {}" , postVO);
		
	}
	
//	@Test
//	public void test1() {
//
//		//RestTemplete
//		RestTemplate restTemplate = new RestTemplate();
//		
//		//url정보, 메서드 형식, 파라미터, 헤더 필요
//		
//		//파라미터 생성
//		//키 하나에 값이 여러개인 multiValueMap
//		//같은 이름으로 파라미터 여러개 보낼 수 있을까? ㅇㅇ가능
//		//get방식이니까 이건 사용 안하고 밑에 getForExtity를 get방식 파라미터 방식으로 설정
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("postId", "1");
//		
//		//요청 정보 객체 생성
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, null);
//		
//		//요청을 전송 후 응답을 처리
//		//get메서드니까 get으로(fake json에서 정해준 형식)
//		//
//		ResponseEntity<PostVO> res =restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/3", PostVO.class, request);
//		//getForObject 해도 됨 얘는 반환타입이 reponseEntity가 아니라, 그냥 PostVO p = restTemplete... 이런식으로 받을 수 있음
//		
//		PostVO result = res.getBody();
//		
//		log.info("result: {} ", result);
//		
//	}
	
}
