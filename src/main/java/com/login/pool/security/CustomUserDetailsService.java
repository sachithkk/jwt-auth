package com.login.pool.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login.pool.dao.UserDao;
import com.login.pool.domain.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userDao.getUserByEmail(email);
		if(user == null) {
			new UsernameNotFoundException("User not found with username or email : " + email);
		}
		return UserPrincipal.create(user);
		
	}

}
