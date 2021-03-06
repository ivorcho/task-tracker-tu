package com.tusofia.taskmanager.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.tusofia.taskmanager.beans.TaskBean;
import com.tusofia.taskmanager.beans.UserBean;
import com.tusofia.taskmanager.entity.Task;
import com.tusofia.taskmanager.entity.Task.TaskStatus;
import com.tusofia.taskmanager.entity.User;

@ManagedBean
@ViewScoped
public class CreateTaskMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dueDate;
	private TaskStatus status;
	private String assignee;
	private String taskName;
	private String description;
	private Date currentDate = new Date();
	private User selectedAssignee;
	private List<User> users;

	@EJB
	private TaskBean taskBean;
	@EJB
	private UserBean userBean;

	@PostConstruct
	public void init() {
		setUsers(userBean.getAllUsers());
	}

	public void createTask() {
		try {
			Task newTask = new Task(taskName, description, dueDate, status,
					selectedAssignee);
			taskBean.saveTask(newTask);
			String message = "Task " + taskName + " created!";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, message, " "));
		} catch (Exception e) {
			String message = e.getMessage();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Failed to create task", message));
		}
	}

	public void createTaskWithCheck() {
		if (assignee.isEmpty()) {
			selectedAssignee = null;
			createTask();
		} else {
			selectedAssignee = userBean.getUserByUsername(assignee);
			if (taskBean.getOpenAndInProgressTasksCount(selectedAssignee) >= 2) {
				RequestContext.getCurrentInstance().execute(
						"PF('confirmDialog').show();");
			} else {
				createTask();
			}
		}
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public TaskBean getTaskBean() {
		return taskBean;
	}

	public void setTaskBean(TaskBean taskBean) {
		this.taskBean = taskBean;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus[] getTaskStatuses() {
		return TaskStatus.values();
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public User getSelectedAssignee() {
		return selectedAssignee;
	}

	public void setSelectedAssignee(User selectedAssignee) {
		this.selectedAssignee = selectedAssignee;
	}
}