package com.example.mini_project_employees.dao;

import com.example.mini_project_employees.enitity.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeDao implements Dao<Employee>{

    private List<Employee> employeeList = new ArrayList<>();
    @Override
    public Optional<Employee> get(int id) {
        return employeeList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public Employee add(Employee employee) {
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee update(Employee updatedEmployee) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == updatedEmployee.getId()) {
                employeeList.set(i, new Employee(updatedEmployee));
                return updatedEmployee;
            }
        }
        return null;
    }


    @Override
    public Employee delete(int id) {
        Optional<Employee> toDelete = get(id);
        toDelete.ifPresent(employeeList::remove);
        return toDelete.orElse(null);
    }
}
