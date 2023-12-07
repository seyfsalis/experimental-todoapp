package com.seyfsalis.todoapp.controller;


import com.seyfsalis.todoapp.dto.TodoAndCategoryDto;
import com.seyfsalis.todoapp.entity.Category;
import com.seyfsalis.todoapp.entity.Todo;
import com.seyfsalis.todoapp.service.CategoryService;
import com.seyfsalis.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;
    private TodoService todoService;

    @Autowired
    public CategoryController(CategoryService categoryService, TodoService todoService) {
        this.categoryService = categoryService;
        this.todoService = todoService;
    }

    @GetMapping("/categories")
    public List<Category> getListOfCategories() {
        return categoryService.findAll();
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        category.setId(0);
        Category category1 = categoryService.save(category);
        return category1;
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable int id) {

        return categoryService.findCategoryAndTodosById(id);
    }

    /*@PutMapping("/categories")
    public Category update(@RequestBody Category category) {
        Category categorydb = categoryService.save(category);

        return categorydb;
    }*/

    @PutMapping("/categories")
    public String associateCategoryAndTodo(@RequestBody TodoAndCategoryDto dto) {
        Category category = categoryService.findById(dto.getCategoriesid());
        Todo todo = todoService.findById(dto.getTodosid());

        category.addTodo(todo);

        categoryService.save(category);

        return "Saved Category id: " + category.getId() + " Todo id: " + todo.getId();
    }
}
