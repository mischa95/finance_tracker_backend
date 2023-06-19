package com.app.financetracker.persistence;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String categoryName;

        @OneToMany()
        @JoinColumn(name = "category_id", referencedColumnName = "id")
        private List<Expense> expenses;

}
