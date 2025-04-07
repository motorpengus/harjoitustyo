package com.harjoitus.tyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.harjoitus.tyo.domain.CategoryRepository;


@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Handler for displaying the list of categories
    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }
}
