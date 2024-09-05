package com.winter.app.members;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String username;
	private String password;
	private String name;
	private String email;
	private Date birth;
	private boolean enabled;
	
	//회원 한명이 권한 여러개 가지고 있음
	private List<RoleVO> vos;
}
