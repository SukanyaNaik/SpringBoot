package com.example.cruddemoemployee.spring.jpa.dao;

import com.example.cruddemoemployee.spring.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
