package com.tusofia.taskmanager.mb;
 
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.tusofia.taskmanager.beans.TaskBean;
import com.tusofia.taskmanager.entity.Task;
import com.tusofia.taskmanager.entity.Task.TaskStatus;
import com.tusofia.taskmanager.entity.User;
 
@ManagedBean
@RequestScoped
public class TaskMB {
         
    private Date dueDate;
	private TaskStatus status;
    private User assignee;
	private String taskName;
    private String description;
    
    private Task managedTask;
    private List<Task> tasks;
    
    @EJB
    private TaskBean taskBean;
    
    @PostConstruct
    public void init() {
        setTasks(taskBean.getAllTasks());
    }
     
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
    
    public void createTask(){
    	Task newTask = new Task(taskName, description, dueDate, status, assignee);
    	taskBean.saveTask(newTask);
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
    
   public void buttonAction(ActionEvent actionEvent) {
       addMessage("Submited!!");
   }
    
   public void addMessage(String summary) {
       FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
       FacesContext.getCurrentInstance().addMessage(null, message);
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

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
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
}