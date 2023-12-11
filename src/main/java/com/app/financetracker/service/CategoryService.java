package com.app.financetracker.service;

import com.app.financetracker.dto.CategoryDTO;
import com.app.financetracker.utils.Mapper;
import com.app.financetracker.exception.CategoryNotEmptyException;
import com.app.financetracker.exception.CategoryNotFoundException;
import com.app.financetracker.persistence.Category;
import com.app.financetracker.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final Mapper customMapper;

    public List<CategoryDTO> findAllCategories(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(customMapper::categoryToDTO).toList();
    }

    public CategoryDTO findCategoryById(Long id) {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(customMapper::categoryToDTO)
                .filter(category -> Objects.equals(category.getId(), id))
                .findFirst()
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO){
        Category category = categoryRepository.save(customMapper.dtoToCategory(categoryDTO));
        return customMapper.categoryToDTO(category);
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
