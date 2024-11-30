
package com.example.InternEmployeeManagementSystem.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.InternEmployeeManagementSystem.exception.ResourceNotFoundException;
import com.example.InternEmployeeManagementSystem.service.EmployeeService;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private EmployeeService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
		// loading user from db
		CustomUserDetail user = service.loadUserByUserId(username);
		if (user != null) {
			return user;
		} else {
			throw new ResourceNotFoundException("User not found with username: " + username);
		}

	}

}
