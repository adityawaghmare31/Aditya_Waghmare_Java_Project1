package com.example.InternEmployeeManagementSystem.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author RAM
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		HashMap<String, Object> map = new HashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedTime = sdf.format(new Date());

		map.put("Time", formattedTime);
		ex.getFieldErrors().forEach(error -> {
			map.put(error.getField(), error.getDefaultMessage());
		});
		return map;
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public HashMap<String, Object> constraintViolationException(ConstraintViolationException ex) {
		HashMap<String, Object> map = new HashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedTime = sdf.format(new Date());
		map.put("Time", formattedTime);
		map.put("Message", ex.getConstraintViolations());

		return map;
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> badCredentialsException(BadCredentialsException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);

	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> invalidCredientials(InvalidCredentialsException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);
	}

	

}
