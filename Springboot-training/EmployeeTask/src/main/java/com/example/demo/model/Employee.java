package com.example.demo.model;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
//smanaging, persisting, and accessing data between Java objects and relational databases.
@Table(name = "employees")
public class Employee implements Serializable{
    
    
//  Serializablde is a process of converting objects into byte stream.
    
//    @NotNull: a constrained CharSequence, Collection, Map, or Array is valid as long as it’s not null, but it can be empty.
//    @NotEmpty: a constrained CharSequence, Collection, Map, or Array is valid as long as it’s not null, and its size/length is greater than zero.
//    @NotBlank: a constrained String is valid as long as it’s not null, and the trimmed length is greater than zero.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "Name")
    //@NotBlank(message = "Name is mandatory")
    private String name;
    
    //@NotBlank(message = "Salary is mandatory")
    @Column(name = "Salary")
    private float salary;

    public Employee(String name, float salary) {
        super();
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
        super();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }
}