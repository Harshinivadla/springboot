package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	public ProductService service;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "WELCOME Harshini";
	}
	
	@GetMapping("/all")
	public List<Product> getAllProducts(){
		return service.getProducts();
	}
	
	public Product getProductById(@PathVariable int id) {
		return service.getProducts(id);
	}
}
