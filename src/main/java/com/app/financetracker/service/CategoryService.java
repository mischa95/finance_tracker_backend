package com.app.financetracker.service;

import com.app.financetracker.exception.CategoryNotFoundException;
import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import com.app.financetracker.repository.CategoryRepository;
import com.app.financetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    public void deleteExpense(Long id){
        categoryRepository.deleteCategoryById(id);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category by id " + id + " was not found"));
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }
}
