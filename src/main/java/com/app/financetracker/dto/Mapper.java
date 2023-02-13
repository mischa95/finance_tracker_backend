package com.app.financetracker.dto;

import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    private static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

//    public static CategoryDTO categoryToDTO(Category category) {
//        Long id = category.getId();
//        String name = category.getCategoryName();
//        List<String> expenses = category
//                .getExpenses()
//                .stream()
//                .map(Expense::getDescription)
//                .collect(Collectors.toList());
//
//        return new CategoryDTO(id, name, expenses);
//    }

    public static CategoryDTO categoryToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public static ExpenseDTO expenseToDTO(Expense expense){
        return modelMapper.map(expense, ExpenseDTO.class);
    }
}
