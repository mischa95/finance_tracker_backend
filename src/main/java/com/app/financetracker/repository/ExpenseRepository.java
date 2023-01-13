package com.app.financetracker.repository;

import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //Optional<Expense> findExpenseByCategory(Category category);
}
