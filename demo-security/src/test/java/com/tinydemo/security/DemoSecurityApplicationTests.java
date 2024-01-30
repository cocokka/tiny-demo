package com.tinydemo.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

//@SpringBootTest
class DemoSecurityApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testEncodePassword() {
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println(encoder.encode("123"));

		Assert.isTrue(encoder.matches("123", encoder.encode("123")), "password not match");
	}
}
