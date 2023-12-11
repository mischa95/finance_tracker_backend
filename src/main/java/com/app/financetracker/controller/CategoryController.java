package com.app.financetracker.controller;

import com.app.financetracker.dto.CategoryDTO;
import com.app.financetracker.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @ResponseBody
    @GetMapping
    public List<CategoryDTO> getCategories(){
        return categoryService.findAllCategories();
    }

    @ResponseBody
    @GetMapping("{id}")
    public CategoryDTO getCategoryById(@PathVariable("id") Long id){
        return categoryService.findCategoryById(id);
    }

    @ResponseBody
    @PostMapping
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
