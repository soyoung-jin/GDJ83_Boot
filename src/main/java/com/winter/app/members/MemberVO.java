package com.winter.app.members;

import java.sql.Date;
import java.util.List;

import com.winter.app.validate.MemberAddGroup;
import com.winter.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberVO {
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	//회원 가입 시 검증해야 한다면 addgroup, 수정 시 검증해야 한다면 update 둘다 주고)
	private String username;
	
	@Pattern(groups = {MemberAddGroup.class}, regexp = "(?=.*[0-9])(?=.*[a-z]).{6,12}")
	//수정시에만 검증
	@NotBlank(groups = {MemberAddGroup.class})
	private String password;
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String name;
	
	@Email(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String email;
	
	@Past (groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private Date birth;
	private boolean enabled;
	
	//회원 한명이 권한 여러개 가지고 있음
	private List<RoleVO> vos;
	
	//비밀번호 확인 변수 추가
	private String passwordCheck;

}
