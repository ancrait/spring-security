package com.sorokaadnriy.security.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private record Product(Integer productId, String productName, double productPrice){}

    private List<Product> products = new ArrayList<>(List.of(
            new Product(1,"Iphone",800.0),
            new Product(2,"Samsung",650.0),
            new Product(3,"Xiaomi",350.0)
    ));

    @GetMapping("/")
    public List<Product> getProducts(){
        return products;
    }

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product){
        products.add(product);
        return product;
    }
}
