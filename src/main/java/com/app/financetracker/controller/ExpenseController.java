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
@CrossOrigin(origins = "http://localhost:4200/")
public class ExpenseController {

    private final ExpenseService expenseService;

    @ResponseBody
    @GetMapping("/all")
    public List<ExpenseDTO> getAllExpenses(){
        return expenseService.findAllExpenses();
    }

    @ResponseBody
    @GetMapping("/findexbyid/{id}")
    public ExpenseDTO getExpenseById(@PathVariable("id") Long id){
        return expenseService.findExpenseById(id);
    }

    @ResponseBody
    @GetMapping("/find/{id}")
    public List<ExpenseDTO> getExpenseByCategoryId(@PathVariable("id") Long id){
        return expenseService.findExpensesByCategory(id);
    }

    @ResponseBody
    @PostMapping("/add")
    public ExpenseDTO addExpense(@RequestBody ExpenseDTO expenseDTO){
        return expenseService.addExpense(expenseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateExpense(@PathVariable("id") Long id, @RequestBody ExpenseDTO expenseDTO){
        expenseService.updateExpense(id, expenseDTO);
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
    @GetMapping("/sumbycat/{id}")
    public Integer getSumOfExpensesByCategoryId(@PathVariable("id") Long id){
        return expenseService.getSumOfExpensesByCategory(id);
    }
}
