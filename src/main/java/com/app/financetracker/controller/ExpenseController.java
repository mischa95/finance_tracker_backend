package com.app.financetracker.controller;

import com.app.financetracker.dto.ExpenseDTO;
import com.app.financetracker.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @ResponseBody
    @GetMapping
    public List<ExpenseDTO> getAllExpenses() {
        return expenseService.findAllExpenses();
    }

    @ResponseBody
    @GetMapping("{id}")
    public ExpenseDTO getExpenseById(@PathVariable("id") Long id) {
        return expenseService.findExpenseById(id);
    }

    @ResponseBody
    @GetMapping("/me")
    public List<ExpenseDTO> getExpenseForCurrentUser(Authentication authentication) {
        return expenseService.findExpensesForCurrentUser(authentication.getName());
    }

    @ResponseBody
    @GetMapping("me/categories/{categoryId}/calculate-percentage")
    public Integer getCategoryPercentage(@PathVariable("categoryId") Long categoryId, Authentication authentication) {
        return expenseService.getCategoryPercentage(categoryId, authentication.getName());
    }

    @ResponseBody
    @GetMapping("/by-category/{categoryId}")
    public List<ExpenseDTO> getExpenseByCategoryId(@PathVariable("id") Long id) {
        return expenseService.findExpensesByCategory(id);
    }

    @ResponseBody
    @PostMapping
    public ExpenseDTO addExpense(@RequestBody ExpenseDTO expenseDTO, Authentication authentication) {
        return expenseService.addExpense(expenseDTO, authentication);
    }

    @PutMapping("{id}")
    public void updateExpense(@PathVariable("id") Long id, @RequestBody ExpenseDTO expenseDTO) {
        expenseService.updateExpense(id, expenseDTO);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public void deleteExpense(@PathVariable("id") Long id) {
        expenseService.deleteExpense(id);
    }

    @ResponseBody
    @GetMapping("/sum")
    public Double getSumOfAllExpenses() {
        return expenseService.getSumOfAllExpenses();
    }

    @ResponseBody
    @GetMapping("sum/by-category/{categoryId}")
    public Double getSumOfExpensesByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return expenseService.getSumOfExpensesByCategory(categoryId);
    }
}
