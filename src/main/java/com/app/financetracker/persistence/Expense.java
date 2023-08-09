package com.app.financetracker.persistence;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer price;
    private String currency;
    private String description;

    @ManyToOne()
    private Category category;
    private LocalDate date;
    @ManyToOne()
    private User user;

}
