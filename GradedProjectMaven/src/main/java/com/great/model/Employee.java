package com.great.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee 
{

	@Id
	private int id;
	private String employeeName;
	private String employeeAddress;
	private String employeePhone;
	private double employeeSalary;

	public Employee() {}

	public Employee(int id, String employeeName, String employeeAddress, String employeePhone, double employeeSalary) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeeAddress = employeeAddress;
		this.employeePhone = employeePhone;
		this.employeeSalary = employeeSalary;
	}

	public int getId() {
		return id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

}
