package org.example.service;

import org.example.model.Employee;

import java.util.*;

public interface EmployeeService {
    Employee saveEmployee(Employee employee) throws Exception;
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long id);
    Employee updateEmployee(Employee updatedEmployee);
    void deleteEmployee(long id);
}
