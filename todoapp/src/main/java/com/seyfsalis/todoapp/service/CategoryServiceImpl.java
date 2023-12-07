package com.seyfsalis.todoapp.service;

import com.seyfsalis.todoapp.dao.CategoryRepository;
import com.seyfsalis.todoapp.entity.Category;
import com.seyfsalis.todoapp.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private EntityManager entityManager;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, EntityManager entityManager) {
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        Optional<Category> result = categoryRepository.findById(id);
        Category category = null;

        if(result.isPresent()) {
            category = result.get();
        } else {
            throw new RuntimeException("Category Not Found with given id - " + id);
        }

        return category;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findCategoryAndTodosById(int id) {
        TypedQuery<Category> query = entityManager.createQuery(
                "select c from Category c "
                        + "JOIN FETCH c.todos "
                        + "where c.id = :data",
                Category.class
        );
        query.setParameter("data", id);

        Category category = query.getSingleResult();


        return category;
    }
}
