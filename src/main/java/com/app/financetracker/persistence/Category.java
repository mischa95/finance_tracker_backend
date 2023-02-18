package com.app.financetracker.persistence;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.mapping.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


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
