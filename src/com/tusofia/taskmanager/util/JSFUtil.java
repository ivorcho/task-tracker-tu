package com.tusofia.taskmanager.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tusofia.taskmanager.entity.User;

public class JSFUtil {

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static javax.faces.application.Application getApplication() {
		return getFacesContext().getApplication();
	}

	public static HttpSession getSession() {
		HttpSession session = (HttpSession) getFacesContext().getExternalContext().getSession(false);
		return session;
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
		return request;
	}

	public static HttpServletResponse getResponse() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		return response;
	}

	public static Object getSessionMapValue(String key) {
		return getFacesContext().getExternalContext().getSessionMap().get(key);
	}

	public static void setSessionMapValue(String key, Object value) {
		getFacesContext().getExternalContext().getSessionMap().put(key, value);
	}

	public static void removeApplciacationMapValue(String key) {
		getFacesContext().getExternalContext().getSessionMap().remove(key);
	}

	public static User getLoggedInUser() {
		Object o = getSession().getAttribute(Constants.SESSION_ATTRIBUTE_LOGGED_IN_USER);
		if (o != null) {
			return (User) o;
		} else {
			return null;
		}
	}

	public static void setLoggedInUser(User user) {
		getSession().setAttribute(Constants.SESSION_ATTRIBUTE_LOGGED_IN_USER, user);
	}

	public static boolean isLoggedIn() {
		return getLoggedInUser() != null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}
}
