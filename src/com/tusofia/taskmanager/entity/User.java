package com.tusofia.taskmanager.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * Entity implementation class for Entity: User
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "findUserByUsername", query = "SELECT u FROM User u WHERE u.username=:username"),
		@NamedQuery(name = "findUserByEmail", query = "SELECT u.email FROM User u WHERE u.email=:email"),
		@NamedQuery(name = "getAllUsers", query = "SELECT u FROM User u")})
public class User implements Serializable {

	private int id;
	private String username;
	private String password;
	private String email;
	private String fullName;
	private AccountType accountType;
	private List<Task> tasks;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String email,
			String fullName, AccountType accountType) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.accountType = accountType;
	}

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotNull
	@Column(unique = true)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@NotNull
	@Column(unique = true)
	@Email
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

	@OneToMany(mappedBy="user")
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public static enum AccountType {
		USER, ADMIN
	}

}
