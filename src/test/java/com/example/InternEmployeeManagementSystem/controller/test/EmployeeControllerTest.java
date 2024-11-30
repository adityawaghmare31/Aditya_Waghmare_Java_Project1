package com.example.InternEmployeeManagementSystem.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.InternEmployeeManagementSystem.controller.EmployeeController;
import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.entity.Roles;
import com.example.InternEmployeeManagementSystem.serviceImpl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	EmployeeServiceImpl employeeServiceImpl;
	
	@Mock
	Set<Roles> mockRoles;
	
	@Test
	public void getUserByIdTest() {
		Employee employee = new Employee();
		employee.setName("aditya waghmare");
		employee.setUsername("aditya");
		employee.setDateofBirth(new Date(1995, 11, 01));
		employee.setDepartment("Developer");
		employee.setEmail("adityawaghmare900@gmail.com");
		employee.setPassword("Aditya");
		employee.setRoles(mockRoles);
		
		when(employeeServiceImpl.getEmployeeById(employee.getUsername())).thenReturn(employee);
		ResponseEntity<Employee> userById = employeeController.getUserById("aditya");
		assertEquals(HttpStatus.OK, userById.getStatusCode());
		assertEquals(employee, userById.getBody());
	}
}
