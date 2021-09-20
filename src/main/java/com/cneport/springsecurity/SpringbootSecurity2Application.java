package com.cneport.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
//@EnableGlobalMethodSecurity源码里面全部默认是false
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true)//开启访问控制注解，如果不开启则无法使用注解，当未登陆时会让登陆，登陆完之后如果未匹配权限不是403，而是500 type=Internal Server Error, status=500
public class SpringbootSecurity2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSecurity2Application.class, args);
	}

}
