package com.app.financetracker.dto;

import com.app.financetracker.persistence.Expense;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {


    private Long id;
    private String name;
    private List<String> expenses;

    public Long getId() { return id; }
    public String getName() {
        return name;
    }
    public List<String> getExpenses() {
        return expenses;
    }
}
