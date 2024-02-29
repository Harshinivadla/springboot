package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Entity.Employee;
import com.example.demo.Repository.Repository;

@SpringBootApplication
public class SpringSecurity1Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity1Application.class, args);
	}

	@Autowired
	public Repository repo;
	
	@Override
	public void run(String... args) throws Exception {
		
		Employee emp1 = new Employee("Harshini", 68000);
		repo.save(emp1);
		
		Employee emp2 = new Employee("Harshu", 30000);
		repo.save(emp2);		
	}
}