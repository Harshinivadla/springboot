package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmployeeException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.security.SecurityConfig;

import org.springframework.cache.annotation.Cacheable;

import jakarta.validation.Valid;

import com.example.demo.model.Employee;

@RestController
@RequestMapping("/employees")
@ComponentScan(basePackages = "com.example.demo")

public class EmployeeController {

    /*  recording information, actions, and events within the app.    
    
    -----Levels in logger -----
    
    info   
    error
    warn       
    trace 
    debug  */
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeRepository employeeRepository;
       
    @Autowired
    public SecurityConfig securityConfig;
    
    @GetMapping("/secure-endpoint")
    @PostMapping("/secure-endpoint")
    @Secured("RLOE_USER")
    public String secureEndpoint() {
        return "This is a secure endpoint!";
    }
    
    @GetMapping
    @Cacheable(value = "employees") 
    public List<Employee> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        if (employees != null) {
            logger.info("Fetched employees");
        }
        else {
            logger.error("List is empty");
        }
        return employees;
    }
    
    @PostMapping
    @Cacheable(value = "employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        if(employee.getName()==null || employee.getName().length()==0) {
            logger.error("Name cannot be null");
            throw new EmployeeException("Name cannot be null");
        }
        else {
            logger.info("New Employee created....");
        }
        return employeeRepository.save(employee);
    }

    @GetMapping("{id}")
    @Cacheable(value = "employees", key = "#id")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee id %d not found",id);
                    return new EmployeeException("Employee id not found"+id);
                });
        logger.info("Fetched employee by ID: {}" ,id);
        return ResponseEntity.ok(employee);
    }
    
    @PutMapping("{id}")
    @CachePut(value = "employees", key = "#id") 
    ResponseEntity<Employee> updateEmployee(@PathVariable int id, @Valid @RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException("Employee not found"+id));
        updateEmployee.setName(employeeDetails.getName());
        updateEmployee.setSalary(employeeDetails.getSalary());
        employeeRepository.save(updateEmployee);
        if(employeeDetails.getName()==null || employeeDetails.getName().length()==0) {
            logger.error("Employee name shouldn't be null");
            throw new EmployeeException("Employee name shouldn't be null");
        }
        else {
            logger.info("Updated successfully..");
        }
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "employees", allEntries = true)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int id){    
        Employee deleteEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException(""));
        employeeRepository.delete(deleteEmployee);
        logger.info("Deleted sucessfully...");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
