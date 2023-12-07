package com.seyfsalis.todoapp.requestmodels;


import lombok.Data;

@Data
public class AddUserRequest {

    private int id;

    private String username;

    private String password;

    private String email;

}
