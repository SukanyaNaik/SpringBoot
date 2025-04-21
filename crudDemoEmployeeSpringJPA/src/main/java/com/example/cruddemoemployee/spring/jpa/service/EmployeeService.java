package com.example.cruddemoemployee.spring.jpa.service;

import com.example.cruddemoemployee.spring.jpa.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
