package com.winter.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	//회원가입
	public int add(MemberVO memberVO) throws Exception{
		int result = memberMapper.add(memberVO);
		
		//회원가입 하면서 권한 추가
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getName());
		map.put("roleNum", 1);
		
		result = memberMapper.addRole(map);
		
		return result;
	}

	//회원조회 로그인
	public MemberVO detail (MemberVO memberVO) throws Exception{
		//null이면 로그인 실패, 정보 조회 내용 없으므로
		MemberVO result = memberMapper.detail(memberVO);
		
		//패스워드 검증은 나중에 나갈 수업내용때문에 서비스에서 함!
		if(result != null) {
			if(result.getPassword().equals(memberVO.getPassword())){
				return result;
			}
		}
		
		return null;
	}

	
}
