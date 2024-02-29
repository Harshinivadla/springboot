package com.example.demo.entity;

public class AuthRequest {

	private String username;
	private String password;
	
	
	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public AuthRequest() {
		super();
	}
	
	
}
