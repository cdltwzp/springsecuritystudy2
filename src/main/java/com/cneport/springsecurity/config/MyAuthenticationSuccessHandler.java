package com.cneport.springsecurity.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
//自定义登陆成功跳转

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private final String forwardUrl;
	
	public MyAuthenticationSuccessHandler(String forwardUrl) {
		//Assert.isTrue(UrlUtils.isValidRedirectUrl(forwardUrl), () -> "'" + forwardUrl + "' is not a valid forward URL");
		this.forwardUrl = forwardUrl;
	}
	
	/**
	 * Authentication :权限 ，认证对象的封装
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//重定向
		System.out.println(authentication.getAuthorities());//权限 [admin,normal]
		System.out.println(authentication.getCredentials());//凭证
		System.out.println(authentication.getDetails());//WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=10D5E013D73AD1F3B19C01277F2294B5]
		System.out.println(authentication.getPrincipal());//org.springframework.security.core.userdetails.User [Username=admin, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, credentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[admin, normal]]
		response.sendRedirect(forwardUrl);
	}

}
