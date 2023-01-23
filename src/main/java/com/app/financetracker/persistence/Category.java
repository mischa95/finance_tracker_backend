package com.app.financetracker.persistence;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.mapping.Array;

import java.util.ArrayList;
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

        @Column(nullable = false)
        private String categoryName;
}
