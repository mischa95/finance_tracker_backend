package com.app.financetracker.controller;

import com.app.financetracker.dto.ExpenseDTO;
import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import com.app.financetracker.persistence.User;
import com.app.financetracker.repository.CategoryRepository;
import com.app.financetracker.repository.ExpenseRepository;
import com.app.financetracker.repository.UserRepository;
import com.app.financetracker.service.ExpenseService;
import com.app.financetracker.utils.Mapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ExpenseServiceTest {

    private static Mapper customMapper;
    private static ModelMapper modelMapper;
    private ExpenseService expenseService;
    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private Authentication authentication;

    @BeforeAll
    static void setMapper(){
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            customMapper = new Mapper(modelMapper);
    }
    @BeforeEach
    void setup(){
        expenseRepository = Mockito.mock(ExpenseRepository.class);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        userRepository = Mockito.mock(UserRepository.class);

        expenseService = new ExpenseService(expenseRepository, categoryRepository, userRepository, customMapper);

        authentication = mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("Test User");

        Mockito.when(userRepository.findByUsername("Test User")).thenReturn(Optional.of(new User()));
    }
    @Test()
    void testDeleteExpense() {
        Long expenseId = 1L;
        Mockito.when(expenseRepository.findById(expenseId)).thenReturn(Optional.of(new Expense()));
        expenseService.deleteExpense(expenseId);

        verify(expenseRepository, times(1)).deleteById(expenseId);
    }

    @Test
    void testGetSumOfAllExpenses() {
        Expense expense1 = new Expense();
        expense1.setPrice(50.0);

        Expense expense2 = new Expense();
        expense2.setPrice(75.0);

        Expense expense3 = new Expense();
        expense3.setPrice(100.0);

        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(expense1);
        expenseList.add(expense2);
        expenseList.add(expense3);

        when(expenseRepository.findAll()).thenReturn(expenseList);

        Double sum = expenseService.getSumOfAllExpenses();

        Double expectedSum = 50.0 + 75.0 + 100.0;

        assertEquals(expectedSum, sum);
    }
    @Test
    void testAddExpense() {

        Expense expense = new Expense();
        expense.setId(1L);
        expense.setDescription("Test Expense");
        expense.setPrice(100.0);

        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);

        ExpenseDTO savedExpense = expenseService.addExpense(customMapper.expenseToDTO(expense), authentication);

        verify(expenseRepository, times(1)).save(any(Expense.class));

        assertNotNull(savedExpense);
        assertEquals("Test Expense", savedExpense.getDescription());
        assertEquals(100.0, savedExpense.getPrice());
    }

    @Test
    void testUpdateExpense() {

        Expense expense = new Expense();
        expense.setId(1L);
        expense.setDescription("Test Expense");
        expense.setPrice(100.0);
        expense.setCategory(new Category());

        Mockito.when(expenseRepository.save(any(Expense.class))).thenReturn(expense);

        ExpenseDTO savedExpense = expenseService.addExpense(customMapper.expenseToDTO(expense), authentication);

        verify(expenseRepository, times(1)).save(any(Expense.class));

        assertNotNull(savedExpense);
        assertEquals("Test Expense", savedExpense.getDescription());
        assertEquals(100.0, savedExpense.getPrice());
        assertEquals(1, savedExpense.getId());

        String updatedDescription = "Updated Expense";
        savedExpense.setDescription(updatedDescription);

        Mockito.when(expenseRepository.findById(savedExpense.getId())).thenReturn(Optional.of(expense));
        expenseService.updateExpense(savedExpense.getId(), savedExpense);

        verify(expenseRepository, times(1)).save(any(Expense.class));

        Expense updatedExpense = customMapper.dtoToExpense(savedExpense);

        Mockito.when(expenseRepository.findById(savedExpense.getId())).thenReturn(Optional.of(updatedExpense));

        ExpenseDTO updatedSavedExpense = expenseService.findExpenseById(savedExpense.getId());

        assertNotNull(updatedSavedExpense);
        assertEquals(updatedDescription, updatedSavedExpense.getDescription());
    }
}
