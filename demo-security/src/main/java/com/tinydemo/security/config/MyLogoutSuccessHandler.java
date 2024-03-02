package com.tinydemo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinydemo.security.entity.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

	private final ObjectMapper objectMapper;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.info("onLogoutSuccess");
		CommonResponse<String> cp = new CommonResponse<>();
		cp.setCode(200).setMessage("success").setData("logout success");
		String json = objectMapper.writeValueAsString(cp);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
