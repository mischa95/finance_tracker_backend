package com.app.financetracker.controller;

import com.app.financetracker.dto.CategoryDTO;
import com.app.financetracker.dto.Mapper;
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
@CrossOrigin(origins = "http://localhost:4200/")
public class CategoryController {
    private final CategoryService categoryService;

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

    @ResponseBody
    @PostMapping("/add")
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
