package com.tinydemo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinydemo.security.entity.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class MyAccessDeniedHandler implements AccessDeniedHandler {

	private final ObjectMapper objectMapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.info("onAuthenticationFailure");
		String exceptionLocalizedMessage = accessDeniedException.getLocalizedMessage();
		CommonResponse<String> cp = new CommonResponse<>();
		cp.setCode(HttpStatus.FORBIDDEN.value()).setMessage("Forbidden").setData(exceptionLocalizedMessage);
		String json = objectMapper.writeValueAsString(cp);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
