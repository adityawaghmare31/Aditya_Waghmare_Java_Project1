package com.example.InternEmployeeManagementSystem.dao;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.entity.Roles;
import com.example.InternEmployeeManagementSystem.security.CustomUserDetail;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
//	public CustomUserDetail loadUserByUserId(String userId);
//
//	public boolean addEmployee(@Valid Employee employee);
//
//	public List<Employee> getAllEmployee();
//
//	public Roles addRole(Roles role);

	Optional<Employee> findById(String userId);
	boolean existsByUsername(String username);
	List<Employee> findByName(String name);
}
