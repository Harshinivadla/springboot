package com.example.demo.controller;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.example.demo.exception.ResourceException;
import com.example.demo.model.AuthRequest;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.JwtService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public List<Employee> employees() {
        List<Employee> employees = repo.findAll();
        logger.info("Fetched employees...");
        return employees;
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
    	if (employee.getName() == null || employee.getName().isEmpty()) {
            logger.error("Name can't be null");
            throw new ResourceException("Name cannot be null or empty");
        }
        logger.info("New Employee added..");
        Employee savedEmployee = repo.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        logger.warn("Fetching employee by ID: {}", id);
        Employee employee = repo.findById(id)
                                .orElseThrow(() -> new ResourceException("Employee not found with ID: " + id));
        return ResponseEntity.ok(employee);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody @NotNull Employee employee) {
        Employee updateEmployee = repo.findById(id)
                .orElseThrow(() -> new ResourceException("Employee not found with ID: " + id));
        updateEmployee.setName(employee.getName());
        updateEmployee.setRole(employee.getRole());
        repo.save(updateEmployee);
        logger.info("Employee updated successfully...");
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        Employee deleteEmployee = repo.findById(id)
                                      .orElseThrow(() -> new ResourceException("Employee not found with ID: " + id));
        repo.delete(deleteEmployee);
        logger.info("Employee deleted successfully...");
        return ResponseEntity.noContent().build();
    }

	@PostMapping("/authenticate")
    public ResponseEntity<String> authenticateAndGetToken(@org.jetbrains.annotations.NotNull @RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new ResourceException("Invalid credentials");
        }
    }
}