package com.tusofia.taskmanager.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.tusofia.taskmanager.beans.UserBean;
import com.tusofia.taskmanager.entity.User;
import com.tusofia.taskmanager.entity.User.AccountType;

@ManagedBean
@ViewScoped
public class CreateUserMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String email;
	private String fullName;
	private AccountType accountType;
	
	@EJB
	private UserBean userBean;
	
	public void createUser(){
		try {
			userBean.saveUser(new User(username, password, email, fullName, accountType));			
		} catch (Exception e) {
			String message = e.getMessage();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
		}
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public AccountType[] getAccountTypes() {
        return AccountType.values();
    }
}
