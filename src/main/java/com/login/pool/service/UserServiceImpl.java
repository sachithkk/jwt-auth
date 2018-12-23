package com.login.pool.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.login.pool.dao.UserDao;
import com.login.pool.domain.Role;
import com.login.pool.domain.User;
import com.login.pool.enums.RoleName;
import com.login.pool.request.UserRequest;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDao userDao;
	
	
	@Override
	@Transactional
	public User addNewUser(UserRequest userRequest) {
		
		String password = userRequest.getPassword();
		String hashPassword = passwordEncoder.encode(password);
		
		String userRole = RoleName.ROLE_USER.toString();
		Role role = userDao.getUserRoles(userRole);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		
		User user = new User();
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setUserName(userRequest.getUserName());
		user.setPassword(hashPassword);
		user.setRoles(roles);
		
		User result = userDao.createUser(user);
		
		if(result != null) {
			return result;
		}
		
		return null;
	}


	@Override
	public User login(String email, String password) {

		
		
		return null;
	}

}
