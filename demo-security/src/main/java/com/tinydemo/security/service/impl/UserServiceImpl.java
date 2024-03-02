package com.tinydemo.security.service.impl;

import com.tinydemo.security.entity.User;
import com.tinydemo.security.mapper.UserMapper;
import com.tinydemo.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsManager, UserDetailsPasswordService {

	private final UserMapper userMapper;

	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		return null;
	}

	@Override
	public void createUser(UserDetails user) {
		User currentUser = new User();
		currentUser.setUsername(user.getUsername()).setPassword(user.getPassword()).setEnabled(user.isEnabled());
		userMapper.save(currentUser);
	}

	@Override
	public void updateUser(UserDetails user) {
		User existingUser = this.userMapper.getByUsername(user.getUsername());
		existingUser.setPassword(user.getPassword()).setEnabled(user.isEnabled());
		userMapper.update(existingUser);
	}

	@Override
	public void deleteUser(String username) {

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	public boolean userExists(String username) {
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		else {
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			if ("lisi".equals(user.getUsername())) {
				authorities.add(() -> "USER_ADD");
			}

			return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(passwordEncoder.encode(user.getPassword()))
				.credentialsExpired(false)
				.accountLocked(false)
				.authorities(authorities)
				.roles("USER")
				.disabled(!user.isEnabled())
				.build();

			// return new
			// org.springframework.security.core.userdetails.User(user.getUsername(),
			// user.getPassword(),
			// user.isEnabled(), true, true, true, authorities);
		}
	}

	@Override
	public List<User> list() {
		return userMapper.list();
	}

	@Override
	public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

	@Override
	public void createUser(User user) {
		// UserDetails userdetails = new
		// org.springframework.security.core.userdetails.User(user.getUsername(),
		// user.getPassword(), new ArrayList<>());
		UserDetails userdetails = org.springframework.security.core.userdetails.User.builder()
			.username(user.getUsername())
			.password(passwordEncoder.encode(user.getPassword()))
			.roles("USER")
			.disabled(!user.isEnabled())
			.build();
		this.createUser(userdetails);
	}

	@Override
	public void updateUser(User user) {
		UserDetails userdetails = org.springframework.security.core.userdetails.User.builder()
			.username(user.getUsername())
			.password(passwordEncoder.encode(user.getPassword()))
			.roles("USER")
			.disabled(!user.isEnabled())
			.build();
		this.updateUser(userdetails);
	}

}
