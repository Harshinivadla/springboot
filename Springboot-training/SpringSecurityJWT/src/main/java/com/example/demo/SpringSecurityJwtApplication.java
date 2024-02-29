package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.User;
import com.example.demo.repository.repository;

@SpringBootApplication
public class SpringSecurityJwtApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	@Autowired
	public repository repo;
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("User1" , "Pwd1");
		repo.save(user1);
		
		User user2 = new User("User2" , "Pwd2");
		repo.save(user2);
	}
}
