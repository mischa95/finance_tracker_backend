package com.app.financetracker.service;

import com.app.financetracker.dto.ExpenseDTO;
import com.app.financetracker.dto.Mapper;
import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import com.app.financetracker.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public List<ExpenseDTO> findAllExpenses(){
        List<Expense> expenseList = expenseRepository.findAll();
        return expenseList.stream().map(Mapper::expenseToDTO).collect(Collectors.toList());
    }

    public List<ExpenseDTO> findExpensesByCategory(Category category){
        List<Expense> expenseList = expenseRepository.findExpenseByCategory(category);
        return expenseList
                .stream()
                .map(Mapper::expenseToDTO)
                .collect(Collectors.toList());
    }

    public Expense addExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id){
        List<Expense> expenseList = expenseRepository.findAll();
        expenseList.removeIf(t -> t.getId().equals(id));
    }

    public Integer getSumOfAllExpenses(){
        List<Expense> expenseList = expenseRepository.findAll();
        return expenseList.stream().mapToInt(o -> o.getPrice()).sum();
    }

    public Integer getSumOfExpensesByCategory(Category category){
        List<Expense> expenseList = expenseRepository.findExpenseByCategory(category);
        return expenseList.stream().mapToInt(o -> o.getPrice()).sum();
    }
}
