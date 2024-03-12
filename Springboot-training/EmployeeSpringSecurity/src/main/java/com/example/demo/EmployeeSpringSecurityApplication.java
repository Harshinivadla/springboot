package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
public class EmployeeSpringSecurityApplication implements CommandLineRunner {

	public static final Logger logger = LoggerFactory.getLogger(EmployeeSpringSecurityApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSpringSecurityApplication.class, args);
		logger.info("Spring Security application started");
	}

	@Autowired
	public EmployeeRepository repo;

	@Override
	public void run(String... args) throws Exception {

		Employee emp1 = new Employee("Admin" , "ADMIN");
		repo.save(emp1);

		Employee emp2 = new Employee("Harshini" , "USER");
		repo.save(emp2);
	}
}