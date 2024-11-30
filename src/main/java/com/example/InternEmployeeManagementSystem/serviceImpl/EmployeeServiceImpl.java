package com.example.InternEmployeeManagementSystem.serviceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.InternEmployeeManagementSystem.dao.RoleRepository;
import com.example.InternEmployeeManagementSystem.dao.EmployeeRepository;
import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.entity.Roles;
import com.example.InternEmployeeManagementSystem.exception.ResourceAlreadyExistsException;
import com.example.InternEmployeeManagementSystem.exception.ResourceNotFoundException;
import com.example.InternEmployeeManagementSystem.security.CustomUserDetail;
import com.example.InternEmployeeManagementSystem.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeRepository dao;

	@Autowired
	RoleRepository roleRepository;

//	@Override
//	public CustomUserDetail loadUserByUserId(String userId) {
//		return dao.loadUserByUserId(userId);
//	}

	public CustomUserDetail loadUserByUserId(String userId) {
		CustomUserDetail user = new CustomUserDetail();
		try {
			Employee employee = dao.findById(userId).orElse(null); // Optional handling
			if (employee != null) {
				user.setUserid(employee.getUsername());
				user.setPassword(employee.getPassword());
				user.setRoles(employee.getRoles());
			}
			System.out.println("load user ..." + user);
		} catch (Exception e) {
			e.printStackTrace(); // Log exception
		}
		return user;
	}

//	@Override
//	public boolean addEmployee(@Valid Employee employee) {
//		//Date date=Date.valueOf(LocalDate.now());
//		String encodedPassword = passwordEncoder.encode(employee.getPassword());
//		employee.setPassword(encodedPassword);
//		
//		boolean isAdded = dao.addEmployee(employee);
//		return isAdded;
//	}

	@Override
	public boolean addEmployee(Employee employee) {
		String encodedPassword = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(encodedPassword);
		if (dao.existsByUsername(employee.getUsername())) {
			throw new ResourceAlreadyExistsException("Employee Already Exist With ID: " + employee.getUsername());
		}
		dao.save(employee); // Automatically persists the entity
		return true;
	}

//	@Override
//	public List<Employee> getAllEmployees() {
//		List<Employee> allEmployee = dao.getAllEmployee();
//		List<Employee> distinctEmployeeList=new ArrayList<>();
//		Set<String> seenEmployeeNames=new HashSet<>();
//		
//		for (Employee employee : allEmployee) {
//			seenEmployeeNames.add(employee.getUsername());
//			distinctEmployeeList.add(employee);
//		}
//		return distinctEmployeeList;
//	}

	public List<Employee> getAllEmployees() {
		try {
			return dao.findAll(); // Fetch all employees
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception
			return null; // Return null or an empty list if needed
		}
	}

//	@Override
//	public Roles addRole(Roles role) {
//		return dao.addRole(role);
//	}

	public Roles addRole(Roles role) {
		try {
			Roles dbRole = roleRepository.findByName(role.getName()); // Check if the role exists
			if (dbRole == null) {
				return roleRepository.save(role); // Save the new role
			} else {
				throw new ResourceAlreadyExistsException("Role already exists with name: " + role.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Return null in case of an error
		}
	}

	public Roles getRoleByName(String name) {
		try {
			return roleRepository.findByName(name); // Use repository's method to fetch role by name
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Return null in case of an error
		}
	}

	@Override
	public boolean deleteUserById(String id) {
		try {
			if (dao.existsById(id)) { // Check if user exists
				dao.deleteById(id); // Delete user by ID
				return true; // Indicate successful deletion
			}
			throw new ResourceNotFoundException("User not found with ID: " + id);

		} catch (ResourceNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(); // Log exception
		}
		return false; // Return false if deletion fails
	}

	@Override
	public Employee updateUser(Employee employee) {
		String password = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(password);
		Employee dbuser = dao.findById(employee.getUsername()).orElse(null);

		if (dbuser != null) {
			// Update employee details

			dbuser.setName(employee.getName());
			dbuser.setUsername(employee.getUsername());
			dbuser.setDateofBirth(employee.getDateofBirth());
			dbuser.setDepartment(employee.getDepartment());
			dbuser.setEmail(employee.getEmail());
			dbuser.setPassword(employee.getPassword());
			dbuser.setRoles(employee.getRoles());

			// Save updated user
			dao.save(dbuser);
			return dbuser;
		}

		return null; // Return null if user doesn't exist
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		return dao.findByName(name);

	}

	@Override
	public Roles getRoleById(long roleId) {
		return roleRepository.findById(roleId).orElse(null);

	}

	@Override
	public Employee getEmployeeById(String id) {
		Optional<Employee> employee = dao.findById(id);

		if (employee.isPresent()) {
		//	employee.get().setPassword("*******");
			return employee.get();
		} else {
			// Handle case where user is not found, e.g., log, return null, etc.
			return null;
		}
	}

}
