package com.tinydemo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 开启web安全配置
@RequiredArgsConstructor
@EnableMethodSecurity // 开启方法级别的权限控制, @PreAuthorize配置了则校验，没有配置则默认拥有访问权限。
public class WebSecurityConfig {

	private final ObjectMapper objectMapper;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize -> authorize.requestMatchers("/webjars/**", "/css/**", "/js/**", "/images/**")
			.permitAll()
			// .requestMatchers("/admin/**")
			// .hasRole("ADMIN")
			// .requestMatchers("/**")
			// .hasRole("USER") // 基于角色资源分配权限（用户-角色-资源）
			.requestMatchers("/user/create")
			.hasAuthority("USER_ADD") // 基于request分配权限（用户-请求-资源）
			.anyRequest()
			.authenticated());
		http.formLogin((formLogin) -> formLogin.usernameParameter("username")
			.passwordParameter("password") // parameter name 与表单字段一致
			.loginPage("/login") // 不加会出现重定向次数过多的异常。解除对login页面的认证。
			.permitAll()
			// .failureUrl("/login?failed")
			// .loginProcessingUrl("/login/process")
			.successHandler(new MyAuthenticationSuccessHandler(objectMapper))
			.failureHandler(new MyAuthenticationFailureHandler(objectMapper)));
		// http.logout((logout) ->
		// logout.invalidateHttpSession(true).deleteCookies("JSESSIONID"));
		http.logout((logout) -> logout.logoutSuccessHandler(new MyLogoutSuccessHandler(objectMapper)));
		http.sessionManagement((session) -> session.maximumSessions(1)
			.expiredSessionStrategy(new MySessionInformationExpiredStrategy(objectMapper))); // 同一个账号同时至多有多少个会话。
		http.exceptionHandling((e) -> {
			e.authenticationEntryPoint(new MyAuthenticationEntryPoint(objectMapper));
			e.accessDeniedHandler(new MyAccessDeniedHandler(objectMapper));
		});
		http.csrf(crsf -> crsf.disable());
		http.cors(Customizer.withDefaults());
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
