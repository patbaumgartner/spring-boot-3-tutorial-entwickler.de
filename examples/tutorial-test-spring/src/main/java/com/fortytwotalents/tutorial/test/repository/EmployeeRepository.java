package com.fortytwotalents.tutorial.test.repository;

import com.fortytwotalents.tutorial.test.domain.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee findByLastName(String lastName);

    List<Employee> findAll();
}