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
public class EmployeeTaskApplication implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(EmployeeTaskApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeTaskApplication.class, args);
		logger.info("Springboot application started");
	}

	@Autowired
	public EmployeeRepository repo;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Employee emp1 = new Employee("Gopal" , 100000);
		repo.save(emp1);
		
		Employee emp2 = new Employee("Harshini" , 68000);
		repo.save(emp2);
		
		Employee emp3 = new Employee("Gayathri" , 70000);
		repo.save(emp3);
		
	}

}
