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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final Mapper modelMapper;

    public List<ExpenseDTO> findAllExpenses() {
        List<Expense> expenseList = expenseRepository.findAll();
        return expenseList.stream().map(expense -> modelMapper.expenseToDTO(expense)).collect(Collectors.toList());
    }

    public List<ExpenseDTO> findExpensesByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        List<Expense> expenseList = expenseRepository.findExpenseByCategory(category);
        return expenseList
                .stream()
                .map(modelMapper::expenseToDTO)
                .collect(Collectors.toList());
    }

    public ExpenseDTO addExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseRepository.save(modelMapper.dtoToExpense(expenseDTO));
        return modelMapper.expenseToDTO(expense);
    }

    public void updateExpense(Long id, ExpenseDTO updatedExpense) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id " + id));
        expense.setDescription(updatedExpense.getDescription());
        expense.setPrice(updatedExpense.getPrice());
        expense.setCurrency(updatedExpense.getCurrency());
        expense.setDate(updatedExpense.getDate());
        expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id " + id));
        expenseRepository.deleteById(id);
    }

    public Integer getSumOfAllExpenses() {
        List<Expense> expenseList = expenseRepository.findAll();
        return expenseList.stream().mapToInt(o -> o.getPrice()).sum();
    }

    public Integer getSumOfExpensesByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        List<Expense> expenseList = expenseRepository.findExpenseByCategory(category);
        return expenseList.stream().mapToInt(o -> o.getPrice()).sum();
    }
}

