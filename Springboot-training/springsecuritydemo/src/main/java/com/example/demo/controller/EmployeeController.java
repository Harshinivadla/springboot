package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.SecurityConfig;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	public EmployeeService service;

	@Autowired
	public EmployeeRepository repo;
	
//	@Autowired
//	public PasswordEncoder passwordEncoder;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "WELCOME WELCOME";
	}
	
	@GetMapping("/all")
	public List<Employee> getAllEmployees(){
		return service.getEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		return service.getEmployee(id);
	}
	
	@PostMapping("/new")
	 public String addNewEmployee(@RequestBody Employee employee) {
		 return service.addUser(employee);
	 }
}