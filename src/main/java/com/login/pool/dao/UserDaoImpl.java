package com.login.pool.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.login.pool.domain.Role;
import com.login.pool.domain.User;
import com.login.pool.enums.RoleName;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User createUser(User user) {
		
		try {
			
			em.persist(user);
			return user;
			
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public boolean isExistUser(String email) {
		
		User user = getUserByEmail(email);
		
		if(user != null) {
			return true; 
		}
		
		return false;
	}

	@Override
	public User getUserByEmail(String email) {

		try {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> query = cb.createQuery(User.class);
			Root<User> root = query.from(User.class);

			query.select(root).where(cb.equal(root.get("email"), email));
			return em.createQuery(query).getSingleResult();
			
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Role getUserRoles(String roleName) {
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Role> query = cb.createQuery(Role.class);
			Root<Role> root = query.from(Role.class);
			
			query.select(root).where(cb.equal(root.get("roleName"), roleName));
			return em.createQuery(query).getSingleResult();
		
		} catch (Exception e) {
			return null;
		}
		
	}

}
