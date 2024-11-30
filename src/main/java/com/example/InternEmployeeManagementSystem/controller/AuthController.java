package com.example.InternEmployeeManagementSystem.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.model.JwtResponse;
import com.example.InternEmployeeManagementSystem.security.CustomUserDetailService;
import com.example.InternEmployeeManagementSystem.service.EmployeeService;
import com.example.InternEmployeeManagementSystem.utility.JwtUtil;

@RestController
@RequestMapping("/login")
public class AuthController {
	private static Logger LOG = LogManager.getLogger(AuthController.class);

	@Autowired
	EmployeeService userService;
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	

	@Autowired
	private AuthenticationManager authenticationManager;

	// completed
	@PostMapping("/login-user")
	
	public ResponseEntity<?> loginAdmin(@RequestBody Employee employee,HttpServletResponse response) throws AuthenticationException {

		
		LOG.info("in Login User = "+employee.getUsername());
	
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        employee.getUsername(),
                        employee.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication); //check 
        final String token = jwtUtil.generateToken(authentication); 
        response.addHeader("token", token);
       return ResponseEntity.ok(new JwtResponse(token));
    
    }

	// send otp api is in email cotroller

	
	
}
