package com.seyfsalis.todoapp.dao;

import com.seyfsalis.todoapp.entity.User;

import java.util.List;

public interface UserDAO {

    void save(User user);

    User findById(Integer id);

    List<User> findAll();

    void update(User user);

    void delete(User user);

    User findUserByEmail(String email);

}
