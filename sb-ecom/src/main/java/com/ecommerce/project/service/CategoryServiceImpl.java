package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

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
        if(category == null) return "categoryId: " + categoryId + " doesn't exist";
        categories.remove(category);
        return "Category with categoryId: " + categoryId + " deleted successfully";
    }
}
