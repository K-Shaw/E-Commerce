package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
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
        categories.remove(category);
        return "Category with categoryId: " + categoryId + " deleted successfully";
    }

    @Override
    public String updateCategory(Long categoryId, Category category) {
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
        return "categoryId: " + categoryId + " Updated";
    }
}
