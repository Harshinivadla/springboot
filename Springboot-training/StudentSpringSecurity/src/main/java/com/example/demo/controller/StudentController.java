package com.example.demo.controller;

//import java.rmi.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@RestController
@RequestMapping("/students")
@EnableMethodSecurity
@ComponentScan(basePackages = "com.example.demo")


public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	public StudentRepository repository;

	@GetMapping("/students")
	//@Cacheable(value = "students")
	public List<Student> students(){
		List<Student> students = repository.findAll();
		if(students != null) {
			logger.info("Fetched students...");
		}else {
			logger.error("List is empty!!");
		}
		return students;
	}
	
//	 @GetMapping
//	 @Cacheable(value = "students")
//	 public List<Student> students() throws ConnectException {
//	    List<Student> students = repository.findAll();
//		if (students != null) {
//		    logger.info("Fetched students...");
//		} else {
//		    logger.error("List is empty!!");
//		}
//		return students;
//	    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/students")
	//@Cacheable(value = "students")
	public Student createStudent(@RequestBody Student student) {
		
		if(student.getFirstName()==null || student.getLastName()==null || student.getFirstName().length()==0 || student.getLastName().length()==0) {
			logger.error("Name cannot be null");
			throw new ResourceException("");
		}else {
			logger.info("New student createed...");
		}
		return repository.save(student);
	}
	
	@GetMapping("/students/{id}")
	//@Cacheable(value = "students", key = "#id")
	public ResponseEntity<Student> getStudentById(@PathVariable int id){
		logger.warn("Enter correct ID");
		Student student = repository.findById(id).orElseThrow(()->  new ResourceException(""));
		logger.warn("Fetched student by ID: {}",id);
		return ResponseEntity.ok(student);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/students/{id}")
	//@CachePut(value = "students", key= "#id")
	public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student){
		Student updatestudent = repository.findById(id).orElseThrow(()-> new ResourceException(""));
		updatestudent.setFirstName(student.getFirstName());
		updatestudent.setLastName(student.getLastName());
		repository.save(updatestudent);
		
		if(student.getFirstName()==null || student.getLastName()==null || student.getFirstName().length()==0 || student.getLastName().length()==0) {
			logger.error("Student name shouldn't be null");
			throw new ResourceException("");
		}
		else {
			logger.info("Updated successfully..");
		}
		
		return ResponseEntity.ok(updatestudent);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/students/{id}")
	//@CacheEvict(value = "students", allEntries = true)
	public ResponseEntity<Student> deleteStudent(@PathVariable int id){
		Student deletestudent = repository.findById(id).orElseThrow(()-> new ResourceException(""));
		repository.delete(deletestudent);
		logger.info("Deleted successfully....");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}