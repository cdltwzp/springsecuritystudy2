package com.cneport.springsecurity.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface MyAccessService {

	/**
	 * 自动判断是否有权限
	 * @param request
	 * @param acAuthentication
	 * @return
	 */
	public boolean hasPermission(HttpServletRequest request,Authentication auAuthentication);
}
