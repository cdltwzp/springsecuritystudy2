package com.cneport.springsecurity.config;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
@Service
public class MyAccessServiceImpl implements MyAccessService {

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
	
		String requestURI = request.getRequestURI();
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails)principal;
			Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();//获取所有权限
			return authorities.contains(new SimpleGrantedAuthority(requestURI));//是否有这个uri的权限，注意这里是将uri作为权限使用了
		}
		
		
		return false;
	}

}
