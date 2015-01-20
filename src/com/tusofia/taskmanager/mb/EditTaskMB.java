package com.tusofia.taskmanager.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.tusofia.taskmanager.beans.CommentBean;
import com.tusofia.taskmanager.beans.TaskBean;
import com.tusofia.taskmanager.beans.UserBean;
import com.tusofia.taskmanager.entity.Comment;
import com.tusofia.taskmanager.entity.Task;
import com.tusofia.taskmanager.entity.Task.TaskStatus;
import com.tusofia.taskmanager.entity.User;
import com.tusofia.taskmanager.util.Constants;
import com.tusofia.taskmanager.util.JSFUtil;

@ManagedBean
@ViewScoped
public class EditTaskMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Task managedTask;
	private String commentContent;
	private List<Comment> comments;
	private User user;
	private List<User> users;
	
	
	@EJB
	UserBean userBean;
	@EJB
	CommentBean commentBean;
	@EJB
	TaskBean taskBean;

	@PostConstruct
	public void init() {
		managedTask = taskBean.getTaskById((int) JSFUtil.getSessionMapValue(Constants.SESSION_ATTRIBUTE_SELECTED_TASK));
		comments = managedTask.getComments();
		user = userBean.getUserByUsername(JSFUtil.getLoggedInUsername());
		users = userBean.getAllUsers();
	}
	
	public void saveComment(){
		Comment comment = new Comment();
		comment.setAuthor(user);
		comment.setContent(commentContent);
		comment.setTask(managedTask);
		comment.setDate(new Date());
		commentBean.saveComment(comment);
		comments.add(comment);
	}
	
	public void commitChanges(ActionEvent actionEvent){
		managedTask = taskBean.updateTask(managedTask);
	}
	public boolean isStatusEditable(){
		return user.getUsername().equals(managedTask.getUser().getUsername());
	}
	
	public TaskStatus[] getTaskStatuses() {
		return TaskStatus.values();
	}
	
	public Task getManagedTask() {
		return managedTask;
	}

	public void setManagedTask(Task managedTask) {
		this.managedTask = managedTask;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
