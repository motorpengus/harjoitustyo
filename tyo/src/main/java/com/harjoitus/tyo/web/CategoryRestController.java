package com.harjoitus.tyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harjoitus.tyo.domain.Category;
import com.harjoitus.tyo.domain.CategoryRepository;

@Controller
public class CategoryRestController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categoryrest")
    public @ResponseBody List<Category> getallProducts(){
        return (List<Category>) categoryRepository.findAll();
    }
    @GetMapping("/categoryrest/{id}")
    public @ResponseBody Optional<Category> getCategoryById(@PathVariable(name="id") Long categoryId){
        return categoryRepository.findById(categoryId);
    }
}
