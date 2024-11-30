package com.example.InternEmployeeManagementSystem.controller;



import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.entity.Roles;
import com.example.InternEmployeeManagementSystem.exception.ResourceAlreadyExistsException;
import com.example.InternEmployeeManagementSystem.exception.ResourceNotFoundException;
import com.example.InternEmployeeManagementSystem.service.EmployeeService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private static Logger LOG = LogManager.getLogger(AdminController.class);

	@Autowired
	EmployeeService userService;

	@PostMapping(value = "/add-user", produces = "application/json")
	public ResponseEntity<Boolean> registerEmployee(@RequestBody @Valid Employee employee) {

		boolean isAdded = userService.addEmployee(employee);
		if (isAdded) {
			LOG.info("Added User :" + employee);
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		}

		else {
			LOG.info("User Already Exixts With >ID:" + employee.getUsername());
			throw new ResourceAlreadyExistsException("User Already Exixts With >ID:" + employee.getUsername());
		}

	}

	@DeleteMapping(value = "/delete-user/{id}", produces = "application/json")
	public ResponseEntity<Boolean> deleteUser(@PathVariable String id) {
		boolean isDeleted = this.userService.deleteUserById(id);
		if (isDeleted==true) {
			LOG.info("User Deleted ID: " + id);
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		} else {
			LOG.info("User Not Found Deleted >ID: " + id);
			throw new ResourceNotFoundException("User Not Found For Delete >ID: " + id);
		}
	}

	@PutMapping(value = "/update-user", produces = "application/json")
	public ResponseEntity<Employee> updateUser(@RequestBody Employee employee) {
		Employee updatedUser = userService.updateUser(employee);
		if (updatedUser != null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		}

		else {
			LOG.info("User Not Found For Update >ID: " + employee.getUsername());
			throw new ResourceNotFoundException("User Not Found For Update >ID:" + employee.getUsername());
		}

	}

	@GetMapping(value = "get-all-user", produces = "application/json")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		  List<Employee> allEmployees = userService.getAllEmployees();
		if (!allEmployees.isEmpty()) {
			LOG.info("User Found");
			return new ResponseEntity<List<Employee>>(allEmployees, HttpStatus.OK);
		} else {
			LOG.info("User Not Found");
			throw new ResourceNotFoundException("User Not Found");
		}
	}

	@PostMapping(value = "/add-role", produces = "application/json")
	public ResponseEntity<Object> addRole(@RequestBody Roles role) {
		Roles userRole = userService.addRole(role);
		if (userRole != null) {
			return new ResponseEntity<Object>(role, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Object>("Role Not Added", HttpStatus.OK);

		}
	}

	@GetMapping(value = "/get-role-by-id/{roleId}", produces = "application/json")
	public ResponseEntity<Roles> getRoleById(@PathVariable long roleId) {
		Roles role = userService.getRoleById(roleId);
		if (role != null) {
			return new ResponseEntity<Roles>(role, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Role Not Found For ID : " + roleId);
		}
	}


	@GetMapping(value = "/get-user-by-name/{name}", produces = "application/json")
	public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String name) {
		List<Employee> list = userService.getEmployeeByName(name);
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User Not Exists For Name : " + name);
		}
	}
	
	

}
