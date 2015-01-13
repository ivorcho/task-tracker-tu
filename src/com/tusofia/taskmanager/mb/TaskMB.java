package com.tusofia.taskmanager.mb;
 
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
 
@ManagedBean
public class TaskMB {
         
    private Date dueDate;
	private String status;
    private String assignee;
	private String taskName;
    private String description;
     
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
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
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
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
}