package com.winter.app.home.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.winter.app.members.MemberVO;
import com.winter.app.members.RoleVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor {
	
	//관리자 권한이 있는 애들만 통과시키자. 권한이 있는 건 vos에서 rolName이 Admin인 친구들 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//멤버받음
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		
		if(memberVO == null) {
			//로그인 안했을 때
			response.sendRedirect("/member/login");
			return false;
		}
		for(RoleVO roleVO : memberVO.getVos()) {
			//로그인은 했는데 권한이 없을 때
			if(roleVO.getRoleName().equals("ROLE_ADMIN")) {
				return false;
			}
		}
		
		request.setAttribute("msg", "관리자 전용");
		request.setAttribute("path", "/");
		//포워드 방식으로 jsp로 가보자
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
		view.forward(request, response);
	
		return false;
		
	}

}
