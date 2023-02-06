package com.app.financetracker.dto;

import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static CategoryDTO categoryToDTO(Category category) {
        Long id = category.getId();
        String name = category.getCategoryName();
        List<String> expenses = category
                .getExpenses()
                .stream()
                .map(Expense::getDescription)
                .collect(Collectors.toList());

        return new CategoryDTO(id, name, expenses);

    }

    public Category dtoToCategory(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName(), new ArrayList<>());
    }

    public static ExpenseDTO expenseToDTO(Expense expense) {
        return new ExpenseDTO(expense.getId(), expense.getPrice(), expense.getCurrency(), expense.getDescription(), expense.getCategory().getCategoryName(),
                expense.getDate());

    }

}
