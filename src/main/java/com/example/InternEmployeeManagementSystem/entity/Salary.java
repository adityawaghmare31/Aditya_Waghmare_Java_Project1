package com.example.InternEmployeeManagementSystem.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String employeeid;
	
	@Column(nullable=false)
	private double amount;
	
	@Column(nullable=false)
	private Date paymentDate;

	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Salary(long id, String employeeid, double amount, Date paymentDate) {
		super();
		this.id = id;
		this.employeeid = employeeid;
		this.amount = amount;
		this.paymentDate = paymentDate;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Salary [id=" + id + ", employeeid=" + employeeid + ", amount=" + amount + ", paymentDate=" + paymentDate
				+ "]";
	}
	
	
}
