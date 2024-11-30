package com.example.InternEmployeeManagementSystem.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;

import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.entity.Roles;
import com.example.InternEmployeeManagementSystem.security.CustomUserDetail;

@Transactional
public interface EmployeeService {
	CustomUserDetail loadUserByUserId(String userId);

	boolean addEmployee(@Valid Employee employee);

	List<Employee> getAllEmployees();

	Roles addRole(Roles role);

	boolean deleteUserById(String id);

	Employee updateUser(Employee user);

	List<Employee> getEmployeeByName(String name);

	Roles getRoleById(long roleId);

	Employee getEmployeeById(String id);


	
	
}
