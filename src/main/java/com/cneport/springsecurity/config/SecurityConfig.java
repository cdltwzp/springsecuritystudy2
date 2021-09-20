package com.cneport.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * Security配置类
 * @author admin
 *
 *WebSecurityConfigurerAdapter:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()//表单登陆
		.loginPage("/login.html")//(1) 自定义登陆页面 配置后不会再进入springsecurity默认登陆页,可以直接进入login.html不拦截 但是其他页面都没有拦截
		.loginProcessingUrl("/login")//(7)自定义登陆逻辑 仍然无法登陆 关闭csrf(9)再次登录之后如果这里是/login则执行loadUserByUsername成功后发现重定向到loaclhost:8080
		//.successForwardUrl("/main.html");//(10) 登陆成功后跳转的页面 结果报错There was an unexpected error (type=Method Not Allowed, status=405).因为这里要求是一个post方法
		.successForwardUrl("/toMain")//(11)改为post请求 因为ForwardAuthenticationSuccessHandler中的onAuthenticationSuccess方法的request.getRequestDispatcher(this.forwardUrl).forward(request, response); 是一个转发而转发的方式决定于原请求方式，原请求方式是post，所以这里也必须是post
		.failureForwardUrl("/toError");//(12)登录失败页面 且error.html必须放行
	    //.usernameParameter("username111").passwordParameter("password111")//(14)自定义用户名和密码的name值
		
		http.csrf().disable();//(8)关闭csrf防护
		
		
		
		http.authorizeRequests()//授权
		.antMatchers("/login.html").permitAll()//(5)放行登陆页面(6)修改之后可以进入登录页，但是点击登录没反应，返回302重定向   发现没有执行loadUserByUsername方法
		.antMatchers("/error.html").permitAll()//(13)放行登陆失败页面
		.anyRequest().authenticated();//(2) 所有请求都必须被认证（登陆了）//(3)登陆后发现还是没有进去，报重定向次数过多,为什么呢? 访问login.html时拦截,跳转至登录页login.html,然后又拦截，一直重定向 页面报：localhost 将您重定向的次数过多。//(4)怎么解决呢？login.html要放行，不能拦截
		
		
		
	}
	
	
}
