package com.example.InternEmployeeManagementSystem.service;

import java.util.Map;

import com.example.InternEmployeeManagementSystem.entity.Attendance.AttendanceStatus;

public interface ReportingService {

	Map<String, Long> getDepartmentDistribution();

	Map<String, Double> getSalaryReport();

	Map<String, Map<AttendanceStatus, Long>> getAttendanceReport();

}
