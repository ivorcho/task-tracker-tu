package com.tusofia.taskmanager.mb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.tusofia.taskmanager.beans.TaskBean;
import com.tusofia.taskmanager.beans.UserBean;
import com.tusofia.taskmanager.entity.Task;
import com.tusofia.taskmanager.entity.Task.TaskStatus;
import com.tusofia.taskmanager.entity.User;

@ManagedBean
@RequestScoped
public class TaskMB {

	private Date dueDate;
	private TaskStatus status;
	private String assignee;
	private String taskName;
	private String description;
	private Date currentDate = new Date();

	private Task managedTask;
	private List<Task> tasks;
	private List<User> users;

	@EJB
	private TaskBean taskBean;
	@EJB
	private UserBean userBean;

	@PostConstruct
	public void init() {
		setTasks(taskBean.getAllTasks());
		setUsers(userBean.getAllUsers());
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
						format.format(event.getObject())));
	}

	public void createTask() {
		User user;
		if(assignee.isEmpty()){
			user = null;
		} else {
			user = userBean.findUserByUsername(assignee);			
		}
		java.sql.Date sqlDate = null;
		if(dueDate != null){
			sqlDate = new java.sql.Date(dueDate.getTime());			
		}
		Task newTask = new Task(taskName, description, sqlDate, status, user);
		taskBean.saveTask(newTask);
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

//	public void buttonAction(ActionEvent actionEvent) {
//		addMessage("Submited!!");
//	}

//	public void addMessage(String summary) {
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
//				summary, null);
//		FacesContext.getCurrentInstance().addMessage(null, message);
//	}

	public String selectTask(Task managedTask) {
		this.managedTask = managedTask;
		return "taskView?faces-redirect=true";
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

	public Task getManagedTask() {
		return managedTask;
	}

	public void setManagedTask(Task managedTask) {
		this.managedTask = managedTask;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
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
}