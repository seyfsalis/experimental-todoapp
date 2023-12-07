package com.seyfsalis.todoapp.controller;

import com.seyfsalis.todoapp.entity.Todo;
import com.seyfsalis.todoapp.entity.User;
import com.seyfsalis.todoapp.exception.TodoErrorResponse;
import com.seyfsalis.todoapp.exception.TodoNotFoundException;
import com.seyfsalis.todoapp.requestmodels.AddUserRequest;
import com.seyfsalis.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private List<User> todos;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/save")
    public void saveUser(@RequestParam AddUserRequest addUserRequest) {

    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User u = userService.findById(id);

       /* if ((id >= todos.size()) || (id < 0)) {
            throw new TodoNotFoundException("Todo id not found - " + id);
        }*/

        return u;
    }

    @GetMapping("/users")
    public List<User> getUsers()  {
        List<User> users = userService.findAll();

        return users;
    }


}
