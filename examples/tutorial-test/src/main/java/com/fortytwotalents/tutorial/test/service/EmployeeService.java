package com.fortytwotalents.tutorial.test.service;

import java.util.List;

import com.fortytwotalents.tutorial.test.domain.Employee;

public interface EmployeeService {

	Employee getEmployeeByName(String name);

	List<Employee> getAllEmployees();
}
