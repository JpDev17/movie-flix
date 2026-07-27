package dev.JpDev17.movieflix.controllers;

import dev.JpDev17.movieflix.entities.Category;
import dev.JpDev17.movieflix.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryService.getAll();
        return categoryList;
    }

    @GetMapping("/get/{id}")
    public Category getByCategoryId(@PathVariable Long id) {
        Optional<Category> categoryId = categoryService.getById(id);
        if(categoryId.isPresent()) {
            return categoryId.get();
        }
        return null;
    }

    @PostMapping("/create")
    public Category saveCategory(@RequestBody Category category) {
        Category newCategory = categoryService.createCategory(category);
        return newCategory;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteByCategoryId(@PathVariable Long id) {
       categoryService.deleteById(id);
    }

}
