package com.spring.springboot.service.impl;

import com.spring.springboot.exception.ResourceNotFoundException;
import com.spring.springboot.model.Employee;
import com.spring.springboot.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    private EmployeeRepo employeeRepo;
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        super();
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee","Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //check if employee is in database
        Employee existingEmployee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeRepo.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id));
        employeeRepo.deleteById(id);

    }
}
