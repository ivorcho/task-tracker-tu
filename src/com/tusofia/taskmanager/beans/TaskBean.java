package com.tusofia.taskmanager.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tusofia.taskmanager.entity.Task;
import com.tusofia.taskmanager.entity.Task.TaskStatus;
import com.tusofia.taskmanager.entity.User;

@Stateless
public class TaskBean {

	@PersistenceContext(unitName = "TaskTracker")
	private EntityManager em;
	
	public void saveTask(Task task){
		em.persist(task);
	}
	
	public Task updateTask(Task task){
		return em.merge(task);
	}
	
	public Task getTaskById(int id){
		Query q = em.createNamedQuery("getTaskById", Task.class).setParameter("id", id);
		return (Task) q.getSingleResult();
	}
	
	public List<Task> getAllTasks(){
		Query q = em.createNamedQuery("getAllTasks", Task.class);
		return q.getResultList();
	}
	
	public Long getOpenAndInProgressTasksCount(User user){
		Query q = em.createQuery("select COUNT(t) from Task t where t.user = :user and (t.status = :openStatus or t.status = :inProgresStatus)");
		q.setParameter("user", user);
		q.setParameter("openStatus", TaskStatus.Open);
		q.setParameter("inProgresStatus", TaskStatus.InProgress);
		return (Long) q.getSingleResult();
	}
}
