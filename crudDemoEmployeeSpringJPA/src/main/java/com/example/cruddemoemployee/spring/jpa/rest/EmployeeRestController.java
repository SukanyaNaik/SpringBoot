package com.example.cruddemoemployee.spring.jpa.rest;

import com.example.cruddemoemployee.spring.jpa.entity.Employee;
import com.example.cruddemoemployee.spring.jpa.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService, ObjectMapper theObjectMapper) {
        employeeService = theEmployeeService;
        objectMapper = theObjectMapper;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findEmployeeById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if(employee == null)
            throw new RuntimeException("Employee id not found: " + employeeId);
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        Employee employee = employeeService.save(theEmployee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee theEmployee) {
        Employee employee = employeeService.save(theEmployee);
        return employee;
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload) {
        Employee employee = employeeService.findById(employeeId);

        if(employee == null)
            throw new RuntimeException("Employee id not found: " + employeeId);
        if(patchPayload.containsKey("id"))
            throw new RuntimeException("Cannot update Employee id");

        Employee employeeToBeUpdated = apply(patchPayload, employee);
        employee = employeeService.save(employeeToBeUpdated);
        return employee;
    }

    private Employee apply(Map <String, Object> patchPayload, Employee employee) {
        //Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);

        //Convert patchPayload object to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        //Merge the patch updates into the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if(employee == null)
            throw new RuntimeException("Employee id not found: " + employeeId);
        employeeService.deleteById(employeeId);
    }
}
