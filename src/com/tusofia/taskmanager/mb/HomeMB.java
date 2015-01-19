package com.tusofia.taskmanager.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.tusofia.taskmanager.beans.TaskBean;
import com.tusofia.taskmanager.entity.Task;
import com.tusofia.taskmanager.util.Constants;
import com.tusofia.taskmanager.util.JSFUtil;

@ManagedBean
@ViewScoped
public class HomeMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Task> tasks;

	@EJB
	private TaskBean taskBean;
	
	@PostConstruct
	public void init() {
		setTasks(taskBean.getAllTasks());
	}
	
	public String selectTask(Task managedTask) {
		JSFUtil.setSessionMapValue(Constants.SESSION_ATTRIBUTE_SELECTED_TASK, managedTask.getId());
		return "taskView?faces-redirect=true";
	}
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
