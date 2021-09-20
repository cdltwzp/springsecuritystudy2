package com.cneport.springsecurity.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping(value="/toMain")//没有指定method属性时 get/post/puth/delete都可以访问
	public String toMain() {
		return "redirect:main.html";
	}
	
	@RequestMapping(value="/toError")//没有指定method属性时 get/post/puth/delete都可以访问
	public String toError() {
		return "redirect:error.html";
	}
	
}
