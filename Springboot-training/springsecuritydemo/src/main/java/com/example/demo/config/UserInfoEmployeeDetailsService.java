package com.example.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class UserInfoEmployeeDetailsService implements UserDetailsService {

	@Autowired
	public EmployeeRepository repo;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> employee=repo.findByName(username);
		return employee.map(UserInfoDetails::new)
				.orElseThrow(()->new UsernameNotFoundException("user not found"+username));
	}
	
//	public String addUser(Employee employee) {
//		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
//		repo.save(employee);
//		return "User added";
//	}
}
