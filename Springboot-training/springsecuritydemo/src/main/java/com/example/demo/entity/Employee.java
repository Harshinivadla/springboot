package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private float salary;

	@Column
	private String password;
	
	@Column
	private String roles;

	public Employee() {
		super();
	}

	
	public Employee(int id, String name, float salary, String password, String roles) {
	super();
	this.id = id;
	this.name = name;
	this.salary = salary;
	this.password = password;
	this.roles = roles;
}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getroles() {
		return roles;
	}
	public void setroles(String roles) {
		this.roles = roles;
	}
	
}