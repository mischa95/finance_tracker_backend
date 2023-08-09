package com.app.financetracker.persistence;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String sex;
    @OneToMany()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Expense> expenses;
}
