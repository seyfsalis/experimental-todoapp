package com.seyfsalis.todoapp.service;

import com.seyfsalis.todoapp.dao.TodoRepository;
import com.seyfsalis.todoapp.entity.Todo;
import com.seyfsalis.todoapp.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private EntityManager entityManager;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository, EntityManager entityManager) {
        this.todoRepository = todoRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findById(int id) {
        Optional<Todo> result = todoRepository.findById(id);
        Todo newTodo = null;

        if (result.isPresent()) {
            newTodo = result.get();
        } else {
            throw new RuntimeException("Did not find todo id - " + id);
        }

        return newTodo;
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteById(int id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<Todo> findTodosByUserId(int id) {
        TypedQuery<Todo> query = entityManager.createQuery("FROM Todo WHERE userId = :user_id", Todo.class);

        query.setParameter("user_id", id);

        return query.getResultList();
    }

    @Override
    public User findUserByIdJoinFetch(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u "
                + "JOIN FETCH u.todos "
                + "where u.id = :data",
                User.class
        );
        query.setParameter("data", id);

        User user = query.getSingleResult();

        return user;
    }

    @Override
    @Transactional
    public Todo updateTodo(Todo todo) {
        Optional<Todo> tempTodo = todoRepository.findById(todo.getId());
        Todo newTodo = null;

        if (tempTodo.isPresent()) {
            newTodo = tempTodo.get();
            entityManager.merge(newTodo);
            entityManager.flush();
        }

        return newTodo;
    }

    @Override
    @Transactional
    public void deleteTodoById(int id) {
        Todo todo = entityManager.find(Todo.class, id);

        entityManager.remove(todo);
    }


}
