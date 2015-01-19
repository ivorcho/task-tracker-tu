package com.tusofia.taskmanager.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tusofia.taskmanager.entity.Comment;

@Stateless
public class CommentBean {

	@PersistenceContext(unitName = "TaskTracker")
	private EntityManager em;
	
	public void saveComment(Comment comment){
		em.persist(comment);
	}
}
