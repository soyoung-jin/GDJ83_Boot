package com.winter.app.members;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service //객체를 만들어줘야하는 것을 잊지말자!
@Slf4j
public class MemberUserService extends DefaultOAuth2UserService implements UserDetailsService {
	@Autowired
	private MemberMapper memberMapper;
	
	
	//소셜 로그인 진행
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		//log.error("oauth2 login: {}", userRequest); // oauth2 login: org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest@60dcb583
		//log.error("Token: {}", userRequest.getAccessToken());//Token: org.springframework.security.oauth2.core.OAuth2AccessToken@cc6c4474
		
		//
		ClientRegistration registration =userRequest.getClientRegistration(); 
		//log.error("ClientId: {}", registration.getClientId()); //ClientId: 21f8201befdad4579acf48690872df38: properties의 restAPI 키, 유저 정보가 아님
		
		String sns =registration.getRegistrationId();
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		if(sns.equals("kakao")) {
			//UserRequest 에서 Kakao에 로그인한 사용자의 정보를 담고 있는 OAuth2User를 꺼냄
			oAuth2User=this.useKakao(oAuth2User);
			log.error("erere===============");
		}
		
		if(sns.equals("naver")) {
					
		}
		return oAuth2User;
	}
	
	//카카오, 네이버, 구글은 꺼내와야 하는 항목이 조금씩 다름
	//카카오라면 얘가 호출됨
	private OAuth2User useKakao(OAuth2User auth2User) throws OAuth2AuthenticationException{
		log.error("=================================");
		
		log.error("ID: {}" , auth2User.getName());//사용자 아이디 3702397147
		log.error("Attribute: {}" , auth2User.getAttributes()); //map 타입으로 키밸류 있음
		//Authorities는 카카오에서 보낸 Authorities임
		log.error("Authorities: {}" , auth2User.getAuthorities());//[OAUTH2_USER, SCOPE_profile_image, SCOPE_profile_nickname]
		
		MemberVO memberVO = new MemberVO();
		
		//닉네임, attibutes안에 있었음 map형태임, 꺼내야함
		Map<String, Object> attribute = auth2User.getAttributes();
		Map<String, Object> properties = (Map<String, Object>) attribute.get("properties");
		log.error("properties: {}", properties);
		
		//memberVO에 정보넣기
		//아이디
		memberVO.setUsername(auth2User.getName());
		//닉네임 Map타입으로 들어가 있음, 꺼내줘야 함
		memberVO.setName(properties.get("nickname").toString());
		
		
		//권한에 관련된 것들 넣기
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_USER");
		list.add(roleVO);
		memberVO.setVos(list);
		
		return memberVO;
	}

	//================================================================
	//나의 서버에서 진행하는 로그인
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

}
