package com.seyfsalis.todoapp.service;

import com.seyfsalis.todoapp.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(int id);
    Category save(Category category);

    void deleteById(int id);

    Category findCategoryAndTodosById(int id);
}
