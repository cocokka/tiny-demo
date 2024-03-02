package com.tinydemo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinydemo.security.entity.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * 认证成功的响应处理。（用于前后端分离）
 */
@Slf4j
@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("onAuthenticationSuccess");
		Object principal = authentication.getPrincipal();
		CommonResponse<Object> cp = new CommonResponse<>();
		cp.setCode(200).setMessage("success").setData(principal);
		String json = objectMapper.writeValueAsString(cp);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
