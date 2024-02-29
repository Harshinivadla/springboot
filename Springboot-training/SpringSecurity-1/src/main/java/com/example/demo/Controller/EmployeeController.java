package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Config.SecurityConfig;
import com.example.demo.Entity.Employee;
import com.example.demo.Repository.Repository;

@RestController
@RequestMapping("/employees")
@ComponentScan(basePackages = "com.example.demo")
public class EmployeeController {

	@Autowired
	public Repository repo;
	
	@Autowired
	public SecurityConfig securityConfig;
	
	@Autowired
	public PasswordEncoder posswordEncoder;
	
	
	@GetMapping
	public String welcome() {
		return "WELCOME WELCOME";
	}
	
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_USER','ROLE_ADMIN')")
	public List<Employee> getAllEmployees(){
        List<Employee> employees = repo.findAll();
		return employees;
	}
	
	
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Optional<Employee> getEmployeeById(@PathVariable int id) {
		Optional<Employee> employee= repo.findById(id);
		return employee;
	}
	
	
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return repo.save(employee);
    }
}
