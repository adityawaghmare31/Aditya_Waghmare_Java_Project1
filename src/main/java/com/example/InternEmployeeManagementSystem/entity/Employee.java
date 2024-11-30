package com.example.InternEmployeeManagementSystem.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Employee {

	@Id
	@Column(name = "UserName",unique = true,nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Invalid username format")	
	private String username;
	
	@NotBlank(message = "First name is manadtory")
	@Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$", message = "Invalid first name")
	private String name;

	@Email(message = "Mail not valid")
	private String email;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateofBirth;

	@NotBlank(message = "Department Name is required")
	private String department;
	
	@Column(name = "Password",nullable = false)
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "At least 8 characters long, one uppercase,lowercase,digit,special character")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<Roles> roles;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [username=" + username + ", name=" + name + ", email=" + email + ", dateofBirth=" + dateofBirth
				+ ", department=" + department + ", password=" + password + ", roles=" + roles + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public Employee(@Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Invalid username format") String username,
			@NotBlank(message = "First name is manadtory") @Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$", message = "Invalid first name") String name,
			@Email(message = "Mail not valid") String email, Date dateofBirth,
			@NotBlank(message = "Department Name is required") String department, String password, Set<Roles> roles) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.dateofBirth = dateofBirth;
		this.department = department;
		this.password = password;
		this.roles = roles;
	}
	
	
}
