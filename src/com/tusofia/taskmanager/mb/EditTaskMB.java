package com.tusofia.taskmanager.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.tusofia.taskmanager.entity.Task;
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

	@PostConstruct
	public void init() {
		setManagedTask((Task) JSFUtil.getSessionMapValue(Constants.SESSION_ATTRIBUTE_SELECTED_TASK));
	}
	
	public Task getManagedTask() {
		return managedTask;
	}

	public void setManagedTask(Task managedTask) {
		this.managedTask = managedTask;
	}
}
