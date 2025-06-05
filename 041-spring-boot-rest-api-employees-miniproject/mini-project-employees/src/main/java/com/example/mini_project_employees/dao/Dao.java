package com.example.mini_project_employees.dao;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface Dao<T> {

    Optional<T> get(int id);

    Collection<T> getAll();

    T add(T t);

    T update(T t);

    T delete(int id);


}
