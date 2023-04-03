package com.fortytwotalents.tutorial.test.service;

import com.fortytwotalents.tutorial.test.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeByName(String name);

    List<Employee> getAllEmployees();
}
