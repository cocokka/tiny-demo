package com.tinydemo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinydemo.security.entity.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * 认证失败的响应处理。（用于前后端分离）
 */
@Slf4j
@RequiredArgsConstructor
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private final ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("onAuthenticationFailure");
		String exceptionLocalizedMessage = exception.getLocalizedMessage();
		CommonResponse<String> cp = new CommonResponse<>();
		cp.setCode(401).setMessage("failure").setData(exceptionLocalizedMessage);
		String json = objectMapper.writeValueAsString(cp);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
