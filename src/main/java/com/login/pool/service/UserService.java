package com.login.pool.service;

import com.login.pool.domain.User;
import com.login.pool.request.UserRequest;

public interface UserService {
	
	public User addNewUser(UserRequest userRequest);
	public User login(String email , String password);
}
