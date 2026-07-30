package dev.JpDev17.movieflix.services;

import dev.JpDev17.movieflix.entities.Category;
import dev.JpDev17.movieflix.mapper.CategoryMapper;
import dev.JpDev17.movieflix.repositories.CategoryRepository;
import dev.JpDev17.movieflix.request.CategoryRequest;
import dev.JpDev17.movieflix.response.CategoryResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    public CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found!"));
        return CategoryMapper.toCategoryResponse(category);
    }

    public CategoryResponse createCategory(CategoryRequest request) {
        Category createdCategory = CategoryMapper.toCategory(request);
        Category newCategory = categoryRepository.save(createdCategory);
        return CategoryMapper.toCategoryResponse(newCategory);
    }
    
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " not found!");
        }
        Category createdCategory = CategoryMapper.toCategory(request);
        createdCategory.setId(id);
        Category newCategory = categoryRepository.save(createdCategory);
        return CategoryMapper.toCategoryResponse(newCategory);
    }

    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " not found!");
        }
        categoryRepository.deleteById(id);
    }
}
