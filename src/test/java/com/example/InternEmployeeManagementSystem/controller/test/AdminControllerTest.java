package com.example.InternEmployeeManagementSystem.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.InternEmployeeManagementSystem.controller.AdminController;
import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.entity.Roles;
import com.example.InternEmployeeManagementSystem.serviceImpl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

	@InjectMocks
	AdminController adminController;

	@Mock
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	Set<Roles> mockRoles;

	@Test
	public void registerEmployeeTest() {
		Employee employee = new Employee();
		employee.setName("aditya waghmare");
		employee.setUsername("aditya");
		employee.setDateofBirth(new Date(1995, 11, 01));
		employee.setDepartment("Developer");
		employee.setEmail("adityawaghmare900@gmail.com");
		employee.setPassword("Aditya");
		employee.setRoles(mockRoles);

		when(employeeServiceImpl.addEmployee(employee)).thenReturn(true);
		ResponseEntity<Boolean> register = adminController.registerEmployee(employee);
		assertEquals(HttpStatus.CREATED, register.getStatusCode());
		assertEquals(true, register.getBody());
	}
	
	
	@Test
	public void deleteUserTest() {
		when(employeeServiceImpl.deleteUserById("aditya")).thenReturn(true);
		ResponseEntity<Boolean> deleteUser=adminController.deleteUser("aditya");
		assertEquals(HttpStatus.OK, deleteUser.getStatusCode());
	}
	
	@Test
	public void updateUserTest() {
		Employee employee = new Employee();
		employee.setName("aditya waghmare");
		employee.setUsername("aditya");
		employee.setDateofBirth(new Date(1995, 11, 01));
		employee.setDepartment("Developer");
		employee.setEmail("adityawaghmare900@gmail.com");
		employee.setPassword("Aditya");
		employee.setRoles(mockRoles);
		
		when(employeeServiceImpl.updateUser(employee)).thenReturn(employee);
		ResponseEntity<Employee> updateUser = adminController.updateUser(employee);
		assertEquals(HttpStatus.CREATED, updateUser.getStatusCode());
		assertEquals(employee, updateUser.getBody());
	}
	
	@Test
	public void getAllEmployeesTest() {
		Employee employee = new Employee();
		employee.setName("aditya waghmare");
		employee.setUsername("aditya");
		employee.setDateofBirth(new Date(01,11,1995));
		employee.setDepartment("Developer");
		employee.setEmail("adityawaghmare900@gmail.com");
		employee.setPassword("Aditya");
		employee.setRoles(mockRoles);
		
		List<Employee>list=new ArrayList<>();
		list.add(employee);
		when(employeeServiceImpl.getAllEmployees()).thenReturn(list);
		ResponseEntity<List<Employee>> allEmployees = adminController.getAllEmployees();
		assertEquals(HttpStatus.OK, allEmployees.getStatusCode());
		assertEquals(list, allEmployees.getBody());
	}
	
	@Test
	public void addRoleTest() {
		Roles roles=new Roles();
		roles.setId(1);
		roles.setName("EMPLOYEE");
		when(employeeServiceImpl.addRole(roles)).thenReturn(roles);
		ResponseEntity<Object> role = adminController.addRole(roles);
		assertEquals(HttpStatus.CREATED, role.getStatusCode());
		assertEquals(roles, role.getBody());
	}
	
	@Test
	public void getRoleByIdTest() {
		Roles roles=new Roles();
		roles.setId(1);
		roles.setName("EMPLOYEE");
		
		when(employeeServiceImpl.getRoleById(1)).thenReturn(roles);
		ResponseEntity<Roles> roleById = adminController.getRoleById(1);
		assertEquals(HttpStatus.OK, roleById.getStatusCode());
		assertEquals(roles, roleById.getBody());
	}
	
	@Test
	public void getEmployeeByNameTest() {
		Employee employee = new Employee();
		employee.setName("aditya waghmare");
		employee.setUsername("aditya");
		employee.setDateofBirth(new Date(01,11,1995));
		employee.setDepartment("Developer");
		employee.setEmail("adityawaghmare900@gmail.com");
		employee.setPassword("Aditya");
		employee.setRoles(mockRoles);
		
		Employee employee2 = new Employee();
		employee2.setName("shitil waghmare");
		employee2.setUsername("shitil");
		employee2.setDateofBirth(new Date(06,05,1993));
		employee2.setDepartment("Tester");
		employee2.setEmail("shitilwaghmare@gmail.com");
		employee2.setPassword("Aditya@123");
		employee2.setRoles(mockRoles);
		
		List<Employee>list=new ArrayList<>();
		list.add(employee);
		list.add(employee2);
		when(employeeServiceImpl.getEmployeeByName(employee.getName())).thenReturn(list);
		ResponseEntity<List<Employee>> employeeByName = adminController.getEmployeeByName("aditya waghmare");
		assertEquals(HttpStatus.OK, employeeByName.getStatusCode());
		assertEquals(list, employeeByName.getBody());
	}
}
