package com.example.mini_project_employees.repository;

import com.example.mini_project_employees.dao.Dao;
import com.example.mini_project_employees.enitity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class EmployeeDAOJpaImpl implements Dao<Employee> {

    private final EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAll() {
        TypedQuery<Employee> query =
                entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Optional<Employee> get(int id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    public Employee add(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public Employee delete(int id) {
        Employee managed = entityManager.find(Employee.class, id);
        if (managed != null) {
            entityManager.remove(managed);
        }
        return managed;
    }
}

