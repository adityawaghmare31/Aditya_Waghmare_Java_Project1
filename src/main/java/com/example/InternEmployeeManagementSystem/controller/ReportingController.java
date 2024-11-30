package com.example.InternEmployeeManagementSystem.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InternEmployeeManagementSystem.entity.Attendance.AttendanceStatus;
import com.example.InternEmployeeManagementSystem.service.ReportingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/reports")
public class ReportingController {

	private static Logger LOG = LogManager.getLogger(EmployeeController.class);

	@Autowired
	private ReportingService reportingService;

	@GetMapping("/department-distribution")
	public ResponseEntity<Map<String, Long>> getDepartmentDistribution() {
		Map<String, Long> departmentDistribution = reportingService.getDepartmentDistribution();
		return ResponseEntity.ok(departmentDistribution);
	}

	@GetMapping("/salary")
	public ResponseEntity<Map<String, Double>> getSalaryReport() {
		Map<String, Double> salaryReport = reportingService.getSalaryReport();
		return ResponseEntity.ok(salaryReport);
	}

	@GetMapping("/attendance")
	public ResponseEntity<Map<String, Map<AttendanceStatus, Long>>> getAttendanceReport() {

		Map<String, Map<AttendanceStatus, Long>> attendanceReport = reportingService.getAttendanceReport();
		return ResponseEntity.ok(attendanceReport);
	}

}
