package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.repository;

@RestController
@RequestMapping("/user")
@ComponentScan(basePackages = "com.example.demo")
public class Controller {

	@Autowired
	public repository repo;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "This is not a secure endpoint";
	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		return repo.save(user);
	}
	
	@GetMapping
	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable int id){
		Optional<User> user = repo.findById(id);
		return ResponseEntity.ok(user);
	}
	    
	public String AunthenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		return null;
		
	}
	
}
