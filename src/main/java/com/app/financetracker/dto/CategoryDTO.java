package com.app.financetracker.dto;

import com.app.financetracker.persistence.Expense;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private List<String> expenses;
}
