package com.harjoitus.tyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.harjoitus.tyo.domain.CategoryRepository;
import com.harjoitus.tyo.domain.Product;
import com.harjoitus.tyo.domain.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @GetMapping("/welcomepage")
    public String welcomePage(Model model) {

        return "welcomepage";
    }
    @GetMapping("/productlist")
    public String showProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "productlist";
    }
    @GetMapping("/addproduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showAddProductForm(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addproduct";
    }
    @PostMapping("/saveproduct")
    public String saveProduct(@ModelAttribute Product product){
        productRepository.save(product);
        return "redirect:/productlist";
    }
    @GetMapping("/deleteproduct/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return "redirect:/productlist";
    }

}
