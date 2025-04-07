package com.harjoitus.tyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harjoitus.tyo.domain.Product;
import com.harjoitus.tyo.domain.ProductRepository;

@Controller

public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public @ResponseBody List<Product> getallProducts(){
        return (List<Product>) productRepository.findAll();
    }
    @GetMapping("/products/{id}")
    public @ResponseBody Optional<Product> getProductById(@PathVariable(name="id") Long productId){
        return productRepository.findById(productId);
    }
    
}
