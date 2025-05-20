package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if(existingCategory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "categoryId: " + categoryId + " Not Found");
        }
        categoryRepository.delete(existingCategory.get());
        return "Category with categoryId: " + categoryId + " deleted successfully";
    }

    @Override
    public String updateCategory(Long categoryId, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if(existingCategory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "categoryId: " + categoryId + " Not Found");
        }
        existingCategory.get().setCategoryName(category.getCategoryName());
        categoryRepository.save(existingCategory.get());
        return "categoryId: " + categoryId + " Updated";
    }
}
