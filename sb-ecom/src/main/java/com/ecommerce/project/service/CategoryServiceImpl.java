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
        List<Category> categories = categoryRepository.findAll();
        Category category = null;
        for(Category x : categories){
            if(Objects.equals(x.getCategoryId(), categoryId)){
                category = x;
                break;
            }
        }
        if(category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "categoryId: " + categoryId + " Not Found");
        }
        categoryRepository.delete(category);
        return "Category with categoryId: " + categoryId + " deleted successfully";
    }

    @Override
    public String updateCategory(Long categoryId, Category category) {
        List<Category> categories = categoryRepository.findAll();
        Category newCategory = null;
        for(Category x : categories){
            if(Objects.equals(x.getCategoryId(), categoryId)){
                newCategory = x;
                break;
            }
        }
        if(newCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "categoryId: " + categoryId + " Not Found");
        }
        newCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(newCategory);
        return "categoryId: " + categoryId + " Updated";
    }
}
