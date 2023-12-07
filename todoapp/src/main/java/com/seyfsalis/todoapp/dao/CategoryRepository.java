package com.seyfsalis.todoapp.dao;

import com.seyfsalis.todoapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {



}
