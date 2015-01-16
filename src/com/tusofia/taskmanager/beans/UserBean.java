package com.tusofia.taskmanager.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tusofia.taskmanager.entity.User;

@Stateless
public class UserBean {

	@PersistenceContext(unitName = "TaskTracker")
	private EntityManager em;
	
	public List<User> getAllUsers(){
		Query q = em.createNamedQuery("getAllUsers", User.class);
		return q.getResultList();
	}

	public void saveUser(User user) throws Exception {
		if (!isUsernameUniqie(user)) {
			throw new Exception("Username is already taken!");
		}
		if (!isEmailUniqie(user)) {
			throw new Exception("Email is already taken!");
		}
		em.persist(user);
	}

	private boolean isUsernameUniqie(User user) {
		Query q = em.createNamedQuery("findUserByName").setParameter(
				"username", user.getUsername());
		if (q.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isEmailUniqie(User user) {
		Query q = em.createNamedQuery("findUserByEmail").setParameter("email",
				user.getEmail());
		if (q.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public User findUserByUsername(String username) {
		Query q = em.createNamedQuery("findUserByUsername").setParameter("username", username);
		User user = (User) q.getSingleResult();
		return user;
	}
}
