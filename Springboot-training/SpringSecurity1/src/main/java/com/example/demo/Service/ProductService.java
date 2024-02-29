package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Product;

@Service
public class ProductService {

    List<Product> productList = null;

    public void loadProductsFromDB() {
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("product" + i)
                        .qty(10) // Assuming fixed quantity for all products for now
                        .price(100) // Assuming fixed price for all products for now
                        .build()
                ).collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
