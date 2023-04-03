package com.fortytwotalents.tutorial.transactions.service;

import java.util.List;
import com.fortytwotalents.tutorial.transactions.domain.Employee;

public interface EmployeeService {
    List<Employee> findByLastName(String lastName);
}