package com.example.mini_project_employees.service;

import com.example.mini_project_employees.enitity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> get(int id);

    List<Employee> getAll();

    Employee add(Employee employee);

    Employee update(Employee employee);

    Employee delete(int id);
}
