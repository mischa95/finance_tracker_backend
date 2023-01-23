package com.app.financetracker.repository;

import com.app.financetracker.persistence.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    void deleteCategoryById(Long id);
}
