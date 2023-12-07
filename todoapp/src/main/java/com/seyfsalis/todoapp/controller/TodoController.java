package com.seyfsalis.todoapp.controller;


import com.seyfsalis.todoapp.entity.Todo;
import com.seyfsalis.todoapp.entity.User;
import com.seyfsalis.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public List<Todo> findAll() {
        return todoService.findAll();
    }

    @GetMapping("/todos/{id}")
    public Todo getTodo(@PathVariable int id) {
        Todo todo = todoService.findById(id);

        if (todo == null) {
            throw new RuntimeException("Todo id not found - " + id);
        }

        return todo;
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo) {
        todo.setId(0);

        Todo tododb = todoService.save(todo);

        return tododb;
    }

    @PutMapping("/todos")
    public Todo updateTodo(@RequestBody Todo todo) {

        return todoService.updateTodo(todo);
    }

    @DeleteMapping("/todos/{id}")
    public String deleteTodo(@PathVariable int id) {
        Todo todo = todoService.findById(id);

        if (todo == null) {
            throw new RuntimeException("Todo id not found - " + id);
        }

        todoService.deleteTodoById(id);

        return "Deleted todo id - " + id;
    }

    @GetMapping("/todos/user/{id}")
    public List<Todo> getTodoByUserId(@PathVariable int id) {
        User user = todoService.findUserByIdJoinFetch(id);
        return user.getTodos();
    }
}
