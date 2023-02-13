package com.app.financetracker.service;

import com.app.financetracker.dto.CategoryDTO;
import com.app.financetracker.dto.ExpenseDTO;
import com.app.financetracker.dto.Mapper;
import com.app.financetracker.exception.CategoryNotFoundException;
import com.app.financetracker.exception.ExpenseNotFoundException;
import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import com.app.financetracker.repository.CategoryRepository;
import com.app.financetracker.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> findAllCategories(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(Mapper::categoryToDTO).collect(Collectors.toList());
    }

    public CategoryDTO findCategoryById(Long id) {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(Mapper::categoryToDTO)
                .filter(category -> Objects.equals(category.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public void updateCategory(Long id, Category updatedCategory) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        category.setCategoryName(updatedCategory.getCategoryName());
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        List<Category> categoryList = categoryRepository.findAll();
        categoryList.removeIf(t -> t.getId().equals(id));
    }
}
