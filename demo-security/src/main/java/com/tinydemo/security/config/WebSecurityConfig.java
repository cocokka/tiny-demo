package com.tinydemo.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize -> authorize.anyRequest().authenticated())
			.formLogin(Customizer.withDefaults())
			.logout((logout) -> logout.invalidateHttpSession(true).deleteCookies("JSESSIONID"));
		http.csrf(crsf -> crsf.disable());
		return http.build();
	}

	/*
	 * @Bean public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
	 * InMemoryUserDetailsManager inMemoryUserDetailsService) throws Exception { return
	 * httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
	 * .userDetailsService(inMemoryUserDetailsService)
	 * .passwordEncoder(bCryptPasswordEncoder()) .and() .build(); }
	 *
	 * @Bean public UserDetailsService inMemoryUserDetailsService(BCryptPasswordEncoder
	 * bCryptPasswordEncoder) { InMemoryUserDetailsManager inMemoryUserDetailsManager =
	 * new InMemoryUserDetailsManager();
	 * inMemoryUserDetailsManager.createUser(User.builder() .username("zhangsan")
	 * .password("123") .passwordEncoder(p -> bCryptPasswordEncoder.encode(p))
	 * .roles("USER") .build()); return inMemoryUserDetailsManager; }
	 */

	// @Bean
	// public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
	// UserDetailsService dBUserDetailsManager) throws Exception {
	// return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
	// .userDetailsService(dBUserDetailsManager)
	// .passwordEncoder(bCryptPasswordEncoder())
	// .and()
	// .build();
	// }

}
