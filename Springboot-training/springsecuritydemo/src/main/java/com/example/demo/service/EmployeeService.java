package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;

@Service
public class EmployeeService {

	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmployeeRepository repo;
	
	List<Employee> employeeList;
	
	@Transactional
	@PostConstruct
	public void loadEmployeesFromDB() {
		if (employeeList == null) {
			employeeList = new ArrayList<>();
		}
		else {
			employeeList.clear();
		}
		
		employeeList.add(new Employee(1, "Gayi", 50000, "gayi", "user"));
		employeeList.add(new Employee(2, "GOpal", 68000, "gopal", "admin"));
	}
	
	public List<Employee> getEmployees(){
		if (employeeList == null) {
			loadEmployeesFromDB();
		}
		return employeeList;
	}
	
	public Employee getEmployee(int id) {
		if (employeeList == null) {
			loadEmployeesFromDB();
		}
		
		return employeeList.stream()
				.filter(employee -> employee.getId() == id)
				.findFirst()
				.orElse(null);
	}
	
	public String addUser(Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		repo.save(employee);
		return "User added";
	}
}
