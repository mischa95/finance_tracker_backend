package com.app.financetracker.dto;

import com.app.financetracker.persistence.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {

    private Long id;
    private Integer price;
    private String currency;
    private String description;
    private String category;
    private String date;

    public Long getId() {return id;}

    public Integer getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }
}