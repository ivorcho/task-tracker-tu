package com.tusofia.taskmanager.entity;

import com.tusofia.taskmanager.entity.User;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity

public class Comment implements Serializable {

	
	private int id;
	private User author;
	private Date date;
	private String content;
	private Task task;
	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
	}   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	
	@ManyToOne
	public User getAuthor() {
		return this.author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@ManyToOne
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
   
}
