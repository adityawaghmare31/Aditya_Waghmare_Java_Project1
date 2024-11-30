package com.example.InternEmployeeManagementSystem.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.InternEmployeeManagementSystem.controller.ReportingController;
import com.example.InternEmployeeManagementSystem.entity.Attendance.AttendanceStatus;
import com.example.InternEmployeeManagementSystem.serviceImpl.ReportingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReportingControllerTest {

	@Mock
	ReportingServiceImpl reportingServiceImpl;
	
	@InjectMocks
	ReportingController reportingController;
	
	@Test
	public void getDepartmentDistributionTest() {
		Map<String, Long>map =new HashMap<>();
		map.put("DEVELOPER", (long) 4);
		map.put("Tester", (long) 2);
		map.put("HR", (long) 1);
		
		when(reportingServiceImpl.getDepartmentDistribution()).thenReturn(map);
		ResponseEntity<Map<String, Long>> departmentDistribution = reportingController.getDepartmentDistribution();
		assertEquals(HttpStatus.OK, departmentDistribution.getStatusCode());
		assertEquals(map, departmentDistribution.getBody());
	}
	
	@Test
	public void getSalaryReportTest() {
		Map<String, Double>map =new HashMap<>();
		map.put("aditya", (double) 120000);
		map.put("shitil", (double) 230000);
		map.put("akshay", (double) 123100);
		
		when(reportingServiceImpl.getSalaryReport()).thenReturn(map);
		ResponseEntity<Map<String, Double>> salaryReport = reportingController.getSalaryReport();
		assertEquals(HttpStatus.OK, salaryReport.getStatusCode());
		assertEquals(map, salaryReport.getBody());
	}
	
	
}
