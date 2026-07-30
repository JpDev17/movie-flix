package dev.JpDev17.movieflix.controllers;

import dev.JpDev17.movieflix.request.CategoryRequest;
import dev.JpDev17.movieflix.response.CategoryResponse;
import dev.JpDev17.movieflix.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categoryList = categoryService.getAll();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryResponse> getByCategoryId(@PathVariable Long id) {
       CategoryResponse category = categoryService.getById(id);
       return ResponseEntity.ok(category);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
        CategoryResponse newCategory = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> updateByCategoryId(@PathVariable Long id, @RequestBody CategoryRequest request) {
        CategoryResponse updatedCategory = categoryService.updateCategory(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteByCategoryId(@PathVariable Long id) {
       categoryService.deleteById(id);
       return ResponseEntity.ok("Category with id " + id + " deleted successfully");
    }
}
