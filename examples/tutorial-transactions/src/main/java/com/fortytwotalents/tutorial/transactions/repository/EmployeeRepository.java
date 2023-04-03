package com.fortytwotalents.tutorial.transactions.repository;

import java.util.List;

import com.fortytwotalents.tutorial.transactions.domain.Employee;

public interface EmployeeRepository {

	List<Employee> findByLastName(String lastName);

}