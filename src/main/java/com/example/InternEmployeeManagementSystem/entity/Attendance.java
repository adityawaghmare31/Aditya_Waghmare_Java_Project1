package com.example.InternEmployeeManagementSystem.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Attendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	@NotNull(message="Employee Id is empty")
	private String employeeid;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date date;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AttendanceStatus status;
	public enum AttendanceStatus{
		PRESENT,
		ABSENT
	}

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendance(long id, @NotNull(message = "Employee Id is empty") String employeeid, Date date,
			AttendanceStatus status) {
		super();
		this.id = id;
		this.employeeid = employeeid;
		this.date = date;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AttendanceStatus getStatus() {
		return status;
	}

	public void setStatus(AttendanceStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", employeeid=" + employeeid + ", date=" + date + ", status=" + status + "]";
	}
	
}
