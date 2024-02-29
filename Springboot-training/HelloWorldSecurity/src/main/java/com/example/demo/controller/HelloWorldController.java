// HelloWorldController.java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello, Admin!";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello, User!";
    }
}
