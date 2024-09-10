package com.winter.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService implements UserDetailsService{

	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			//새로 받은 memberVO를 새로 받아줘야 한다.
			memberVO =memberMapper.detail(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//memverVO가 userDetail 타입임 그래서 return
		return memberVO;
	}
	
	//회원가입
	public int add(MemberVO memberVO) throws Exception{
		//인코딩해서 DB에 넣어준다.
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		
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
	
	//비밀번호 검증 메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean check=false;
		//check=false : 검증성공(error 없음)
		//check=true  : 검증실패(error 있음)
		
		//1. 기본 검증값 annotation 검증 결과
		//검증의 결과를 받아올 bindingResult 추가
		//true면 에러가 있겠고, false면 에러가 없겠지요
		check = bindingResult.hasErrors();
		
		//2. password가 일치하는지 검증
		//비교를 위해 파라미터로 memberVO 받아옴
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check=true;
			//에러메세지
			//bindingResult.rejectValue("멤버변수명(path)", "properties의key(코드)");
			bindingResult.rejectValue("passwordCheck", "memberVO.pw.notEqual");
			
		}
		
		//3. id가 중복 인지 검증
		MemberVO result = memberMapper.detail(memberVO);
		//이미 얘도 데이터가 저장되어 있다는 의미
		if(result != null) {
			check = true;
			bindingResult.rejectValue("idCheck", "memberVO.id.exist");
		}
		return check;
		
	}
	
}
