package com.test.test.service;

import com.test.test.model.Employee;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    //save
    Employee saveEmployee(Employee employee) throws ConfigDataResourceNotFoundException;

    //getAll
    List<Employee>getAllEmployees();

    //getById
    Optional<Employee>getEmployeeById(Long id);

    //updateById
    Employee updateEmployeeById(Employee updateEmployee);

    //deleteById
    void deleteEmployeeById(Long id);
}
