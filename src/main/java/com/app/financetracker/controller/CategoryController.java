package com.app.financetracker.controller;

import com.app.financetracker.dto.CategoryDTO;
import com.app.financetracker.persistence.Category;
import com.app.financetracker.service.CategoryService;
import com.app.financetracker.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ExpenseService expenseService;

    @ResponseBody
    @GetMapping("/all")
    public List<CategoryDTO> getCategories(){
        return categoryService.findAllCategories();
    }

    @ResponseBody
    @GetMapping("/find/{id}")
    public CategoryDTO getCategoryById(@PathVariable("id") Long id){
        return categoryService.findCategoryById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category newCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category newCategory = categoryService.updateCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }
}
