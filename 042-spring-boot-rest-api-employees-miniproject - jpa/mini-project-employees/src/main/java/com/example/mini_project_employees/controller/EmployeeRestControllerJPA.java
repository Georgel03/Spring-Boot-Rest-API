package com.example.mini_project_employees.controller;

import com.example.mini_project_employees.enitity.Employee;
import com.example.mini_project_employees.service.EmployeeServiceImpl;
import com.example.mini_project_employees.service.EmployeeServiceImplJPA;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/jpa")
public class EmployeeRestControllerJPA {

    private EmployeeServiceImplJPA employeeService;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeRestControllerJPA(EmployeeServiceImplJPA employeeService,
                                  ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable int id) {
        return employeeService.get(id);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.add(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayLoad) {
        Optional<Employee> tempEmployee = employeeService.get(employeeId);
        if (tempEmployee.isEmpty()) {
            throw new RuntimeException("Employee id not found _ " + employeeId);
        }

        if (patchPayLoad.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body _ " + employeeId);
        }
        Employee patchedEmployee = apply(patchPayLoad, tempEmployee);
        return employeeService.update(patchedEmployee);

    }


    @DeleteMapping("/employees/{id}")
    public Employee deleteEmployee(@PathVariable int id) {

        if (employeeService.get(id).isEmpty()) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        return employeeService.delete(id);
    }

    private Employee apply(Map<String, Object> patchPayLoad, Optional<Employee> tempEmployee) {
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);
        ObjectNode payLoadNode = objectMapper.convertValue(patchPayLoad, ObjectNode.class);
        employeeNode.setAll(payLoadNode);
        return objectMapper.convertValue(employeeNode, Employee.class);
    }
}
