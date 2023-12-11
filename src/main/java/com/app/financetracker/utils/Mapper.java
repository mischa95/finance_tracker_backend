package com.app.financetracker.utils;

import com.app.financetracker.dto.CategoryDTO;
import com.app.financetracker.dto.ExpenseDTO;
import com.app.financetracker.dto.UserDTO;
import com.app.financetracker.persistence.User;
import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;

    public CategoryDTO categoryToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
    public Category dtoToCategory(CategoryDTO categoryDTO){ return modelMapper.map(categoryDTO, Category.class);}
    public ExpenseDTO expenseToDTO(Expense expense){
        return modelMapper.map(expense, ExpenseDTO.class);
    }
    public Expense dtoToExpense(ExpenseDTO expenseDTO){ return modelMapper.map(expenseDTO, Expense.class);}
    public UserDTO userToDTO(User account){
        return modelMapper.map(account, UserDTO.class);
    }
    public User dtoToUser(UserDTO accountDTO){ return modelMapper.map(accountDTO, User.class);}
}
