package com.app.financetracker.dto;

import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

}
