package com.example.Java_Jpa.repository;

import com.example.Java_Jpa.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}