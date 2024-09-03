package com.winter.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	public List<NoticeVO> getList(Long num) throws Exception;
	//여기까지만 치면 우리가 알아서 해줄게 라는 것임. 인터페이스니까 선언부만 존재함
	//메서드를 사용하려면 객체부터 만들어야 하는데, 인터페이스는 객체 생성 불가, 그걸 스프링이 해줌
	//@Mapper라는 어노테이션을 추가한다.
	//DAO는 전부 인터페이스로 만든다.
	
	public NoticeVO getInsert() throws Exception;

}
