package com.login.pool.dao;

import com.login.pool.domain.Role;
import com.login.pool.domain.User;

public interface UserDao {
	
	public User createUser(User user);
	public boolean isExistUser(String email);
	public User getUserByEmail(String email);
	public Role getUserRoles(String roleName);
}
