package com.cneport.springsecurity;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
@SpringBootTest 
//@RunWith(SpringRunner.class) // 底层用junit SpringJUnit4ClassRunner
public class SpringbootSecurity2ApplicationTests {

	@Test
	public void contextLoads() {
		PasswordEncoder passwordEncoderTest = new BCryptPasswordEncoder();
				String encode = passwordEncoderTest.encode("123456");
		System.out.println(encode);
		boolean isMatches = passwordEncoderTest.matches("123456", encode);
		System.out.println(isMatches);
	}

}
