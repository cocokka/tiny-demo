package com.tinydemo.security.service;

import com.tinydemo.security.entity.User;

import java.util.List;

public interface UserService {

	List<User> list();

	User getByUsername(String username);

	void createUser(User user);

	void updateUser(User user);

}
