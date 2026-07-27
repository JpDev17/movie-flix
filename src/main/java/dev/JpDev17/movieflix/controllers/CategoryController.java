package dev.JpDev17.movieflix.controllers;

import dev.JpDev17.movieflix.entities.Category;
import dev.JpDev17.movieflix.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        return categoryList;
    }


}
