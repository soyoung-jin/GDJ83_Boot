package com.winter.app.home.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	//컨트롤러 들어오기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object object = request.getSession().getAttribute("member");
		
		if(object != null){
			return true;
		}else {
			//리다이렉트 방식
			response.sendRedirect("/member/login");
			//웬만해선 포워드로 보내서 로그인 실패 시 메세지를 주는 것이 좋음
			return false;
		}
	}
	
	//컨트롤러 나갈 때 >> 필요한 것들을 골라서 오버라이딩 하면 됨
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	//jsp에서 랜더링 후 나갈 때
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	

}
