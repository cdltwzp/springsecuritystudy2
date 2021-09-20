package com.cneport.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//正常流程
		 //1、根据username查询数据库
		if (!"admin".equals(username)) {
			throw new UsernameNotFoundException("用户名或密码错误!!!");
		}
		
		//2、根据查询的对象比较密码
		String encodePassword = passwordEncoder.encode("123456");
		//3、返回用户对象 数据库中的
		// AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal") 权限
		//Cannot pass a null GrantedAuthority collection 权限不能为空，实现里面自己用逗号分隔
		//权限： admin,normal 角色：ROLE_abc，角色必须以ROLE_为前缀
		return new User("admin", encodePassword, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_abc"));
	}

}
