package com.example.demo.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtService {

	public String generateToken(String username) {
		Map<String,Object> claims = new HashMap<>();
		return createToken(claims,username);
	}

	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey()).compact();
	}

	private Key getSignKey() {
		byte[] keyBytes=Decoders.BASE64.decode("6815c1e97f5a9daf033662535df8e1515e71d47f8b9eea4164d63a2b6d0e3751");
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
