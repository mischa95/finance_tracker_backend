package com.app.financetracker.controller;
import com.app.financetracker.dto.CategoryDTO;
import com.app.financetracker.dto.ExpenseDTO;
import com.app.financetracker.dto.Mapper;
import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import com.app.financetracker.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @ResponseBody
    @GetMapping("/all")
    public List<ExpenseDTO> getAllExpenses(){
        return expenseService.findAllExpenses();
    }

    @ResponseBody
    @GetMapping("/find/{category}")
    public List<ExpenseDTO> getExpenseByCategory(@PathVariable("category") Category category){
        return expenseService.findExpensesByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense){
        Expense newExpense = expenseService.addExpense(expense);
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateExpense(@PathVariable("id") Long id, @RequestBody Expense expense){
        expenseService.updateExpense(id, expense);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public void deleteExpense(@PathVariable("id") Long id){
        expenseService.deleteExpense(id);
    }

    @ResponseBody
    @GetMapping("/sumall")
    public Integer getSumOfAllExpenses(){
        return expenseService.getSumOfAllExpenses();
    }

    @ResponseBody
    @GetMapping("/sumbycat/{category}")
    public Integer getSumOfExpensesByCategory(@PathVariable("category") Category category){
        return expenseService.getSumOfExpensesByCategory(category);
    }
}
