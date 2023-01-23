package com.app.financetracker.service;

import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import com.app.financetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public List<Expense> findAllExpenses(){
        return expenseRepository.findAll();
    }

    public List<Expense> findExpensesByCategory(Category category){
        return expenseRepository.findExpenseByCategory(category);
    }
    public void deleteExpense(Long id){
        expenseRepository.deleteExpenseById(id);
    }

    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
