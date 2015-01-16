package com.tusofia.taskmanager.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tusofia.taskmanager.entity.Task;

@Stateless
public class TaskBean {

	@PersistenceContext(unitName = "TaskTracker")
	private EntityManager em;
	
	public void saveTask(Task task){
		em.persist(task);
	}
	
	public List<Task> getAllTasks(){
		Query q = em.createNamedQuery("getAllTasks", Task.class);
		return q.getResultList();
	}
}
