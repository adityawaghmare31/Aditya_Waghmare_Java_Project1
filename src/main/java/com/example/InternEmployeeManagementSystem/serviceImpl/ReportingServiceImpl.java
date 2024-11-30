package com.example.InternEmployeeManagementSystem.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InternEmployeeManagementSystem.dao.AttendanceRepository;
import com.example.InternEmployeeManagementSystem.dao.EmployeeRepository;
import com.example.InternEmployeeManagementSystem.dao.SalaryRepository;
import com.example.InternEmployeeManagementSystem.entity.Attendance;
import com.example.InternEmployeeManagementSystem.entity.Attendance.AttendanceStatus;
import com.example.InternEmployeeManagementSystem.entity.Employee;
import com.example.InternEmployeeManagementSystem.entity.Salary;
import com.example.InternEmployeeManagementSystem.service.ReportingService;

@Service
public class ReportingServiceImpl implements ReportingService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SalaryRepository salaryRepository;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	

	@Override
	public Map<String, Long> getDepartmentDistribution() {
		return employeeRepository.findAll().stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.counting()));
	}



	@Override
	public Map<String, Double> getSalaryReport() {
		return salaryRepository.findAll().stream().collect(Collectors.groupingBy(Salary::getEmployeeid,
				Collectors.summingDouble(Salary::getAmount)));
	}

	@Override
	public Map<String, Map<AttendanceStatus, Long>> getAttendanceReport() {
		List<Attendance> attendanceRecords = attendanceRepository.findAll();

		return attendanceRecords.stream()
		.collect(Collectors.groupingBy(Attendance::getEmployeeid,
				Collectors.groupingBy(Attendance::getStatus,
						Collectors.counting())));
		
	}

}
