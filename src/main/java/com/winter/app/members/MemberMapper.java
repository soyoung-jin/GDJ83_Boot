package com.winter.app.members;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	//회원가입
	public int add(MemberVO memberVO) throws Exception;

	//회원조회 로그인
	public MemberVO detail (MemberVO memberVO) throws Exception;
	
	//권한 추가
	public int addRole(Map<String, Object> map) throws Exception;
}
