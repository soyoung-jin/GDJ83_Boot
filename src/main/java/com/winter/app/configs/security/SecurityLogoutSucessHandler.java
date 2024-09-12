package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLogoutSucessHandler implements LogoutSuccessHandler {
	@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
			// TODO Auto-generated method stub
			
		}
}
