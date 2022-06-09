package com.huynhthanhnha.restExample.service;

import java.util.List;

import com.huynhthanhnha.restExample.entity.User;
import com.huynhthanhnha.restExample.repository.UserRepository;

public class UserService {

	private final UserRepository userRepository;
	
	public UserService() {
		this.userRepository = new UserRepository();
	}
	
	public List<User> getUsers() {
		return userRepository.getUsers();
	}
	
	public User getUser(int id) {
		return userRepository.getUser(id);
	}
	
	public User save(User user) {
		return userRepository.saveUser(user);
	}
	
	public User update(Integer id, User user) {
		return userRepository.updateUser(id, user);
	}
	
	public void delete(Integer id) {
		userRepository.delete(id);
	}
}
