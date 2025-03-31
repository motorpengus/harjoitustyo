package com.harjoitus.tyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.harjoitus.tyo.domain.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @GetMapping("/welcomepage")
    public String welcomePage(Model model) {
        
        return "welcomepage";
    }
}
