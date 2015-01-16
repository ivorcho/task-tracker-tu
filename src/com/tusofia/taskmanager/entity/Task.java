package com.tusofia.taskmanager.entity;

import java.io.Serializable;
import java.lang.String;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Task
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "getAllTasks", query = "SELECT t FROM Task t")})
public class Task implements Serializable {

	
	private int id;
	private String name;
	private String description;
	private Date dueDate;
	private TaskStatus status;
	private User user;
	private static final long serialVersionUID = 1L;

	public Task() {
		super();
	}   
	
	
	public Task(String name, String description, Date dueDate,
			TaskStatus status, User user) {
		super();
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
		this.user = user;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Enumerated(EnumType.STRING)
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public static enum TaskStatus {
		Open, InProgress, Completed
	}
   
}
