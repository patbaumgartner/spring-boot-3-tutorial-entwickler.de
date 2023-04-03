package com.fortytwotalents.tutorial.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortytwotalents.tutorial.test.domain.Employee;
import com.fortytwotalents.tutorial.test.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployeeByName(String name) {
		return employeeRepository.findByLastName(name);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

}