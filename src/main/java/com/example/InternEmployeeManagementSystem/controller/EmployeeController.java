package com.example.InternEmployeeManagementSystem.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.exception.ResourceNotFoundException;
import com.example.InternEmployeeManagementSystem.service.EmployeeService;

@RestController
@RequestMapping("/user")
public class EmployeeController {

	private static Logger LOG = LogManager.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value = "get-user-by-id/{id}", produces = "application/json")
	public ResponseEntity<Employee> getUserById(@PathVariable String id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee != null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} else {
			LOG.info("User Not Found  >ID: " + id);
			throw new ResourceNotFoundException("User Not Found >ID:" + id);
		}
	}
}
