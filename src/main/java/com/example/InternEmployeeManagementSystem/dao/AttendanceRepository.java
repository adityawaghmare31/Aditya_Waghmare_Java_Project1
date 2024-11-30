package com.example.InternEmployeeManagementSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InternEmployeeManagementSystem.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
