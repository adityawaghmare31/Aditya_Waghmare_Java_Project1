package com.example.InternEmployeeManagementSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InternEmployeeManagementSystem.entity.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Long>{

}
