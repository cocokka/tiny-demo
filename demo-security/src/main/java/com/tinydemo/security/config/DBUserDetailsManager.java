package com.tinydemo.security.config;

import com.tinydemo.security.entity.User;
import com.tinydemo.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;

//@Component

/**
 * can be merged into UserServiceImpl
 *
 * @see com.tinydemo.security.service.impl.UserServiceImpl
 */
public class DBUserDetailsManager /*
									 * implements UserDetailsManager,
									 * UserDetailsPasswordService
									 */ {

	/*
	 * @Autowired private UserMapper userMapper;
	 *
	 * @Override public UserDetails updatePassword(UserDetails user, String newPassword) {
	 * return null; }
	 *
	 * @Override public void createUser(UserDetails user) { User currentUser = new User();
	 * currentUser.setUsername(user.getUsername()).setPassword(user.getPassword()).
	 * setEnabled(user.isEnabled()); userMapper.save(currentUser); }
	 *
	 * @Override public void updateUser(UserDetails user) {
	 *
	 * }
	 *
	 * @Override public void deleteUser(String username) {
	 *
	 * }
	 *
	 * @Override public void changePassword(String oldPassword, String newPassword) {
	 *
	 * }
	 *
	 * @Override public boolean userExists(String username) { return false; }
	 *
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { User user = userMapper.getByUsername(username); if
	 * (user == null) { throw new UsernameNotFoundException("User " + username +
	 * " not found"); } else { Collection<? extends GrantedAuthority> authorities = new
	 * ArrayList<>(); return new
	 * org.springframework.security.core.userdetails.User(user.getUsername(),
	 * user.getPassword(), user.getEnabled(), true, true, true, authorities); } }
	 */

}
