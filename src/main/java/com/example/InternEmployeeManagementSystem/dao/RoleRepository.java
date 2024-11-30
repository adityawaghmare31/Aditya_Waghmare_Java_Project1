package com.example.InternEmployeeManagementSystem.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InternEmployeeManagementSystem.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {
	Roles findByName(String name);
}
