package com.seyfsalis.todoapp.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todos")
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "todo")
    private String todo;

    @Column(name = "duedate")
    private String dueDate;

    @Column(name = "duetime")
    private String dueTime;

    @Column(name = "priority")
    private int priority;

    @Column(name = "status")
    private String status;

    /*@Column(name = "user_id")
    private int userId;*/

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("todos")
    private User userId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(
            name = "todoscategories",
            joinColumns = @JoinColumn(name = "todosid"),
            inverseJoinColumns = @JoinColumn(name = "categoriesid")
    )
    @JsonIgnoreProperties("todos")
    private List<Category> categories;

    public Todo() {}

    public Todo(String todo, String dueDate, String dueTime, int priority, String status) {
        this.todo = todo;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.priority = priority;
        this.status = status;
    }

    public void addCategory(Category category) {

        if (categories == null) {
            categories = new ArrayList<>();
        }

        categories.add(category);
    }
}
