package com.example.InternEmployeeManagementSystem.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.InternEmployeeManagementSystem.dao.EmployeeRepository;
import com.example.InternEmployeeManagementSystem.dao.RoleRepository;
import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.entity.Roles;
import com.example.InternEmployeeManagementSystem.serviceImpl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	RoleRepository roleRepository;
	
	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;
	
	@Mock
	Set<Roles> mockRoles;
	
//	@Test
//	public void loadUserByUserId() {
//		Employee employee=new Employee();
//		employee.setName("aditya waghmare");
//		employee.setUsername("aditya");
//		employee.setDateofBirth(new Date(1995, 11, 01));
//		employee.setDepartment("Developer");
//		employee.setEmail("adityawaghmare900@gmail.com");
//		employee.setPassword("Aditya");
//		employee.setRoles(mockRoles);
//	
//		when(employeeRepository.findById("aditya")).thenReturn(Optional<Employee>employee);
//	}
	
//	@Test
//	public void addEmployeeTest() {
//		Employee employee=new Employee();
//		employee.setName("aditya waghmare");
//		employee.setUsername("aditya");
//		employee.setDateofBirth(new Date(1995, 11, 01));
//		employee.setDepartment("Developer");
//		employee.setEmail("adityawaghmare900@gmail.com");
//		employee.setPassword("Aditya");
//		employee.setRoles(mockRoles);
//		when(employeeRepository.save(employee)).thenReturn(employee);
//		boolean employee2 = employeeServiceImpl.addEmployee(employee);
//		assertEquals(true, employee2);
//	}
	
	@Test
	public void getAllEmployeeTest() {
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
		when(employeeRepository.findAll()).thenReturn(list);
		List<Employee> allEmployees = employeeServiceImpl.getAllEmployees();
		assertEquals(list, allEmployees);
	}
	
	@Test
	public void getRoleByNameTest() {
		Roles roles=new Roles();
		roles.setId(1);
		roles.setName("Developer");
		when(roleRepository.findByName(roles.getName())).thenReturn(roles);
		Roles roleByName = employeeServiceImpl.getRoleByName("Developer");
		assertEquals(roles, roleByName);
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
		
		List<Employee>list=new ArrayList<>();
		list.add(employee);
		when(employeeRepository.findByName(employee.getName())).thenReturn(list);
		List<Employee> employeeByName = employeeServiceImpl.getEmployeeByName("aditya waghmare");
		assertEquals(employee, employeeByName);
	}
}
