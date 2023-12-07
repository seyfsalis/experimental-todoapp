package com.seyfsalis.todoapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(
            name = "todoscategories",
            joinColumns = @JoinColumn(name = "categoriesid"),
            inverseJoinColumns = @JoinColumn(name = "todosid")
    )
    @JsonIgnoreProperties("categories")
    private List<Todo> todos;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public void addTodo(Todo todo) {

        if (todos == null) {
            todos = new ArrayList<>();
        }

        todos.add(todo);
    }

}
