package com.tinydemo.security.controller;

import com.tinydemo.security.entity.User;
import com.tinydemo.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@RequestMapping("/all")
	public List<User> list() {
		return userService.list();
	}

	@PostMapping("/create")
	public void create(@RequestBody User user) {
		this.userService.createUser(user);
	}

	@PutMapping("/update")
	public void update(@RequestBody User user) {
		this.userService.updateUser(user);
	}

}
