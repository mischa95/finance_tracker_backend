package com.app.financetracker.service;

import com.app.financetracker.dto.CategoryDTO;
import com.app.financetracker.dto.ExpenseDTO;
import com.app.financetracker.dto.Mapper;
import com.app.financetracker.exception.CategoryNotEmptyException;
import com.app.financetracker.exception.CategoryNotFoundException;
import com.app.financetracker.exception.ExpenseNotFoundException;
import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import com.app.financetracker.repository.CategoryRepository;
import com.app.financetracker.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final Mapper modelMapper;

    public List<CategoryDTO> findAllCategories(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(modelMapper::categoryToDTO).collect(Collectors.toList());
    }

    public CategoryDTO findCategoryById(Long id) {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(modelMapper::categoryToDTO)
                .filter(category -> Objects.equals(category.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO){
        Category category = categoryRepository.save(modelMapper.dtoToCategory(categoryDTO));
        return modelMapper.categoryToDTO(category);
    }

    public void updateCategory(Long id, CategoryDTO updatedCategory) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        category.setCategoryName(updatedCategory.getCategoryName());
    }

    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        if(!category.getExpenses().isEmpty()) throw new CategoryNotEmptyException("Category " + category.getCategoryName() + " is not empty");
        categoryRepository.deleteById(id);
    }
}
