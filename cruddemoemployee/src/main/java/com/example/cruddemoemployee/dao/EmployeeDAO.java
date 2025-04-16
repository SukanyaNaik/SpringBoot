package com.example.cruddemoemployee.dao;

import com.example.cruddemoemployee.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}
