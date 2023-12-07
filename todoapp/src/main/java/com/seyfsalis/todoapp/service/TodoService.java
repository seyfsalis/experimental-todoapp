package com.seyfsalis.todoapp.service;

import com.seyfsalis.todoapp.entity.Todo;
import com.seyfsalis.todoapp.entity.User;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();

    Todo findById(int id);

    Todo save(Todo todo);

    void deleteById(int id);

    List<Todo> findTodosByUserId(int id);

    User findUserByIdJoinFetch(int id);

    Todo updateTodo(Todo todo);

    void deleteTodoById(int id);
}
