package com.test.test.service;

import com.test.test.model.Employee;
import com.test.test.repo.EmployeeRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    //save employee
    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee Already Exist With Given Email:" + employee.getEmail());
        }

        return employeeRepository.save(employee);
    }

    //get all employee
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    //get employee by id
    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    //update by id
    @Override
    public Employee updateEmployeeById(Employee updateEmployee) {
        return employeeRepository.save(updateEmployee);
    }
    //delete by id
    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);

    }
}
