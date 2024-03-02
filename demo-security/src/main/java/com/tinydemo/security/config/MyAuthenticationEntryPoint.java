package com.tinydemo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinydemo.security.entity.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * 当访问需要认证的页面或者接口时，未登录时，请求未认证的处理（所要跳转到的登录页面配置）。
 */
@Slf4j
@RequiredArgsConstructor
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.info("commence");
		String exceptionLocalizedMessage = authException.getLocalizedMessage();
		CommonResponse<String> cp = new CommonResponse<>();
		cp.setCode(HttpStatus.UNAUTHORIZED.value()).setMessage("Unauthorized").setData(exceptionLocalizedMessage);
		String json = objectMapper.writeValueAsString(cp);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
