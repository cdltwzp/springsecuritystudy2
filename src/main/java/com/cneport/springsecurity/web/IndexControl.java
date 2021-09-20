package com.cneport.springsecurity.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexControl {

	@RequestMapping(value="/login")
	public @ResponseBody String login() {
		return "redirect:main.html";
	}
	//@Secured("ROLE_abc")//(27)有ROLE_abc角色才可以访问,注意这里的角色必须加上ROLE_前缀
	//(28)@Secured是专门用于判断是否具有角色的，能写在方法或类上，参数要以ROLE_前缀
	@PreAuthorize("hasAuthority(\"admin\")")//(29)方法前判断权限，PostAuthorize执行后判断权限 且EnableGlobalMethodSecurity必须开启prePostEnable=true 参数是access表达式
	@RequestMapping(value="/toMain")//没有指定method属性时 get/post/puth/delete都可以访问
	public String toMain() {
		return "redirect:main.html";
	}
	
	@RequestMapping(value="/toError")//没有指定method属性时 get/post/puth/delete都可以访问
	public String toError() {
		return "redirect:error.html";
	}
	
}
