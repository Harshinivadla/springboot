package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@SpringBootApplication
//@EnableCaching
public class StudentSpringSecurityApplication implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(StudentSpringSecurityApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(StudentSpringSecurityApplication.class, args);
	}

	@Autowired
	public StudentRepository repository;
	
	@Override
	public void run(String... args) throws Exception {

		Student student1 = new Student("Harshini", "Harshu");
		repository.save(student1);
		
		Student student2 = new Student();
		student2.setFirstName("Gayathri");
		student2.setLastName("Gopal");
		repository.save(student2);
		
	}
}
