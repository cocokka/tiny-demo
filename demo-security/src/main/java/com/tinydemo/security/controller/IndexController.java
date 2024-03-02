package com.tinydemo.security.controller;

import com.tinydemo.security.entity.CommonResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.ok;

@Controller
public class IndexController {

	@GetMapping("/")
	@ResponseBody
	public CommonResponse<Authentication> index() {
		// 获取当前用户信息。
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		Object principal = authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Object credentials = authentication.getCredentials(); // 密码，数据脱敏。
		Object details = authentication.getDetails();

		CommonResponse<Authentication> response = new CommonResponse<>();
		response.setCode(200).setMessage("success").setData(authentication);
		return response;
	}

}
