package com.app.financetracker.service;

import com.app.financetracker.dto.ExpenseDTO;
import com.app.financetracker.utils.Mapper;
import com.app.financetracker.exception.CategoryNotFoundException;
import com.app.financetracker.exception.ExpenseNotFoundException;
import com.app.financetracker.exception.UserNotFoundException;
import com.app.financetracker.persistence.User;
import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import com.app.financetracker.repository.CategoryRepository;
import com.app.financetracker.repository.ExpenseRepository;
import com.app.financetracker.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
@Transactional
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final Mapper customMapper;

    public List<ExpenseDTO> findAllExpenses() {
        List<Expense> expenseList = expenseRepository.findAll();
        return expenseList.stream().map(customMapper::expenseToDTO).collect(Collectors.toList());
    }

    public List<ExpenseDTO> findExpensesForCurrentUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with id " + username));
        List<Expense> expenseList = user.getExpenses();
        return expenseList
                .stream()
                .map(customMapper::expenseToDTO)
                .collect(Collectors.toList());
    }

    public Integer getCategoryPercentage(Long id, String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with id " + username));
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        List<Expense> expenseList = user.getExpenses();
        double costALlExpenses = expenseList.stream().mapToDouble(Expense::getPrice).sum();
        double costCategoryExpenses = expenseList.stream()
                .filter(expense -> Objects.equals(expense.getCategory(), category)).mapToDouble(Expense::getPrice).sum();
        return (int) ((costCategoryExpenses*100)/costALlExpenses);
    }

    public ExpenseDTO findExpenseById(Long id) {
        List<Expense> expenseList = expenseRepository.findAll();
        return expenseList.stream()
                .map(customMapper::expenseToDTO)
                .filter(expense -> Objects.equals(expense.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id " + id));
    }

    public List<ExpenseDTO> findExpensesByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        List<Expense> expenseList = category.getExpenses();
        return expenseList
                .stream()
                .map(customMapper::expenseToDTO)
                .collect(Collectors.toList());
    }

    public ExpenseDTO addExpense(ExpenseDTO expenseDTO, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Details not found"));
        expenseDTO.setUser(customMapper.userToDTO(user));
        Expense expense = expenseRepository.save(customMapper.dtoToExpense(expenseDTO));
        return customMapper.expenseToDTO(expense);
    }

    public void updateExpense(Long id, ExpenseDTO updatedExpense) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id " + id));
        expense.setDescription(updatedExpense.getDescription());
        expense.setPrice(updatedExpense.getPrice());
        expense.setCategory(customMapper.dtoToCategory(updatedExpense.getCategory()));
        expense.setCurrency(updatedExpense.getCurrency());
        expense.setDate(updatedExpense.getDate());
    }

    public void deleteExpense(Long id) {
        expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id " + id));
        expenseRepository.deleteById(id);
    }

    public Double getSumOfAllExpenses() {
        List<Expense> expenseList = expenseRepository.findAll();
        return expenseList.stream().mapToDouble(Expense::getPrice).sum();
    }

    public Double getSumOfExpensesByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        List<Expense> expenseList = category.getExpenses();
        return expenseList.stream().mapToDouble(Expense::getPrice).sum();
    }
}

