package com.fortytwotalents.tutorial.cache.service;

import java.util.List;
import com.fortytwotalents.tutorial.cache.domain.Employee;

public interface EmployeeService {
	List<Employee> findByLastName(String lastName);
}