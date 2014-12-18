package com.tusofia.taskmanager.mb;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.tusofia.taskmanager.beans.UserBean;
import com.tusofia.taskmanager.entity.User.AccountType;

@ManagedBean
@RequestScoped
public class LoginMB {
	private static final Logger log = Logger.getLogger(LoginMB.class.getName());

	private String username;
	private String password;
	
	@EJB
	private UserBean userBean;

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.login(this.username, this.password);
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Login failed."));
			return "";
		}
		if (request.isUserInRole(AccountType.ADMIN.toString())) {
			return "admin/createUser?faces-redirect=true";
		} else if (request.isUserInRole(AccountType.USER.toString())) {
			return "user/home?faces-redirect=true";
		} else {
			return "";
		}
//		User user = new User(username, password);
//		user.setAccountType(AccountType.ADMIN);
//		user.setEmail("asd@asd.asd");
//		userBean.saveUser(user);
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.logout();
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Logout failed."));
		}
		return "/login?faces-redirect=true";
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
}
