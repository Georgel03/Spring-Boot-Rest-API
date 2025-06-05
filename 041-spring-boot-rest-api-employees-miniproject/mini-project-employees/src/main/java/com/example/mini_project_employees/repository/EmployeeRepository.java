package com.example.mini_project_employees.repository;

import com.example.mini_project_employees.enitity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
