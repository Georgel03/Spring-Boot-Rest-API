package com.example.mini_project_employees.service;

import com.example.mini_project_employees.enitity.Employee;
import com.example.mini_project_employees.repository.EmployeeDAOJpaImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAOJpaImpl employeeDAOJpa;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAOJpaImpl employeeDAOJpa) {
        this.employeeDAOJpa = employeeDAOJpa;
    }

    @Override
    public Optional<Employee> get(int id) {
        return employeeDAOJpa.get(id);
    }

    @Override
    public List<Employee> getAll() {
        return (List<Employee>) employeeDAOJpa.getAll();
    }

    @Transactional
    @Override
    public Employee add(Employee employee) {
        return employeeDAOJpa.add(employee);
    }

    @Transactional
    @Override
    public Employee update(Employee employee) {
        return employeeDAOJpa.update(employee);
    }

    @Transactional
    @Override
    public Employee delete(int id) {
        return employeeDAOJpa.delete(id);
    }
}
