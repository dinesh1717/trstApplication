package com.test.test.controller;


import com.test.test.model.Employee;
import com.test.test.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    //create or save employee
    @PostMapping("/save")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    //Get all employee
    @GetMapping("/getAll")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    //Get employee by id
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeService.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //update employee by id
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,
                                                   @RequestBody Employee employee) {
        return employeeService.getEmployeeById(employeeId)
                .map(savedEmployee -> {

                    savedEmployee.setFirstName(employee.getFirstName());
                    savedEmployee.setLastName(employee.getLastName());
                    savedEmployee.setEmail(employee.getEmail());

                    Employee updatedEmployee = employeeService.updateEmployeeById(savedEmployee);
                    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //delete employee by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId) {

        employeeService.deleteEmployeeById(employeeId);

        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);

    }

}
