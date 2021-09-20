package com.cneport.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
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

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()//表单登陆
		.loginPage("/login.html")//(1) 自定义登陆页面 配置后不会再进入springsecurity默认登陆页,可以直接进入login.html不拦截 但是其他页面都没有拦截
		.loginProcessingUrl("/login")//(7)自定义登陆逻辑 仍然无法登陆 关闭csrf(9)再次登录之后如果这里是/login则执行loadUserByUsername成功后发现重定向到loaclhost:8080
		//.successForwardUrl("/main.html");//(10) 登陆成功后跳转的页面 结果报错There was an unexpected error (type=Method Not Allowed, status=405).因为这里要求是一个post方法
		.successForwardUrl("/toMain")//(11)改为post请求 因为ForwardAuthenticationSuccessHandler中的onAuthenticationSuccess方法的request.getRequestDispatcher(this.forwardUrl).forward(request, response); 是一个转发而转发的方式决定于原请求方式，原请求方式是post，所以这里也必须是post
		//.successHandler(new MyAuthenticationSuccessHandler("https://www.baidu.com/"))//(15)如果不想用上面的转发自定义重定向的话可以自定义MyAuthenticationSuccessHandler，可以自定义成功后的跳转。 对于前后端分离的，可以通过返回某字符串，在前端Controller里面进行重定向
		.failureForwardUrl("/toError");//(12)登录失败页面 且error.html必须放行
		//.failureHandler()(//(16)自定义失败跳转一样AuthenticationFailureHandler 不同的是认证失败了要设置异常，
	    //.usernameParameter("username111").passwordParameter("password111")//(14)自定义用户名和密码的name值
		
		http.csrf().disable();//(8)关闭csrf防护
		
		
		
		http.authorizeRequests()//授权
		//.antMatchers("/login.html").permitAll()//(5)放行登陆页面(6)修改之后可以进入登录页，但是点击登录没反应，返回302重定向   发现没有执行loadUserByUsername方法
		.antMatchers("/login.html").access("permitAll()")//(26)所有的权限控制底层都是access表达式
		.antMatchers("/error.html").permitAll()//(13)放行登陆失败页面
		.antMatchers(HttpMethod.GET,"/image/**").permitAll()//(17) url控制访问 还可以加请求方式
		//.regexMatchers("").permitAll()//(18)正则匹配
		//(19)ExpressionUrlAuthorizationConfigurer中定义了所有的权限控制permitAll(放行所有) denyAll(拒绝所有) anonymous(匿名) authenticated(需要认证) fullyAuthenticated(不允许记住我的方式需要完整的用户名+密码的认证) rememberMe(记住我登陆)
		//.antMatchers("/main1.html").hasAuthority("admin1")//(20)admin可以访问这个页面,因为这个用户有admin,normal两个权限 如果不匹配，则返回type=Forbidden, status=403 ,严格区分大小写
		//.antMatchers("/main1.html").hasAnyAuthority("admin1","admin")//(21)多个权限
		//.antMatchers("/main1.html").hasRole("abcd")//(22)角色匹配，必须再UserServiceImpl中的ROLE_后面的部分 不匹配则报403
		//.antMatchers("/main1.html").hasAnyRole("abcd")//(23)匹配多角色
		//.antMatchers("/main1.html").hasIpAddress("127.0.0.1")//(24)根据IP地址匹配
		.anyRequest().authenticated();//(2) 所有请求都必须被认证（登陆了）//(3)登陆后发现还是没有进去，报重定向次数过多,为什么呢? 访问login.html时拦截,跳转至登录页login.html,然后又拦截，一直重定向 页面报：localhost 将您重定向的次数过多。//(4)怎么解决呢？login.html要放行，不能拦截
		//.anyRequest().access("@myAccessServiceImpl.hasPermission(request,authentication)");//(26)access表达式自定义access方法
		
		//异常处理
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);//(25)自定义403的处理
		
		
	}
	
	
}
