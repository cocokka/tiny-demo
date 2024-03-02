package com.tinydemo.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinydemo.security.entity.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * 同一个账号会话个数超出配置时的处理。 被顶掉的账号会话被踢掉。
 */
@Slf4j
@RequiredArgsConstructor
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

	private final ObjectMapper objectMapper;

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		log.info("onExpiredSessionDetected");
		CommonResponse<SessionInformationExpiredEvent> cp = new CommonResponse<>();
		cp.setCode(HttpStatus.OK.value()).setMessage("login from other device.");
		String json = objectMapper.writeValueAsString(cp);
		HttpServletResponse response = event.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
