package com.app.financetracker.repository;

import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findExpenseByCategory(Category category);
}
