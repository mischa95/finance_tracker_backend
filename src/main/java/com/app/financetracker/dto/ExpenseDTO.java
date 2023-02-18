package com.app.financetracker.dto;

import com.app.financetracker.persistence.Category;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {

    private Long id;
    private Integer price;
    private String currency;
    private String description;
    private CategoryDTO category;
    private LocalDate date;

}