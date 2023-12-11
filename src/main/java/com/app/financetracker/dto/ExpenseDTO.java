package com.app.financetracker.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {

    private Long id;
    private Double price;
    private String currency;
    private String description;
    private CategoryDTO category;
    private LocalDate date;
    private UserDTO user;
}