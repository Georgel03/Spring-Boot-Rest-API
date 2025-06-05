package com.example.mini_project_employees.service;

import com.example.mini_project_employees.enitity.Employee;
import com.example.mini_project_employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplJPA implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImplJPA(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Employee> get(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee delete(int id) {
        Optional<Employee> deletedEmployee = employeeRepository.findById(id);
        if (deletedEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return deletedEmployee.get();
        } else {
            throw new RuntimeException("Employee with id " + id + " not found.");
        }
    }


}
