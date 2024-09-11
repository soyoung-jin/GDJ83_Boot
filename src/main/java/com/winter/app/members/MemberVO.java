package com.winter.app.members;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.winter.app.validate.MemberAddGroup;
import com.winter.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberVO implements UserDetails {
	
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
	//enabled는 userDetails 인터페이스 오버라이딩 해야하는 메서드이다.
	private boolean enabled;
	
	//회원 한명이 권한 여러개 가지고 있음
	private List<RoleVO> vos;
	
	//비밀번호 확인 변수 추가
	private String passwordCheck;

	//security 오버라이딩
	//어떤 타입인지는 모르지만, Grant를 extends 받은 하위타입임.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO : vos) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleVO.getRoleName());
			authorities.add(grantedAuthority);
		}
		
		
		return authorities;
	}

	//계정 만료 여부, false면 만료되고 로그인 안됨
	//false: 사용자 계정의 유효기간이 만료되었습니다.
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정 잠김 여부, false면 만료되고 로그인 안됨
	//false: 사용자 계정이 잠겨있습니다.
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	//비밀번호가 만료됐는지 안됐는지 false면 만료, 로그인 안됨
	//false: 자격 증명 유효 기간이 만료되었습니다.
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//계정 활성화 할건지 말건지
	//enabled 변수 getter메서드 오버라이딩
	//false: 유효하지 않은 사용자입니다.
	public boolean isEnabled() {
		return true;
	}
	
	
	

}
