package com.tinydemo.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class LoginController {

	@RequestMapping("/login")
	public String login() {
		log.info("visit login page...");
		return "login";
	}

}
