package com.app.financetracker.persistence;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Category")
@Table(
        name = "category",
        uniqueConstraints = {
                @UniqueConstraint(name = "category_name_unique", columnNames = "category_name")
        })

public class Category {

        @Id
        @SequenceGenerator(
                name = "category_sequence",
                sequenceName = "category_sequence",
                allocationSize = 1)
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "category_sequence")

        @Column(
                name = "id",
                updatable = false)
        private Long id;

        @Column(
                name = "category_name",
                nullable = false)
        private String categoryName;

        //@OneToMany
        //private List<Expense> expenses;

        public Category(String categoryName) {
                this.categoryName = categoryName;
        }
}
