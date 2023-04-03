package com.fortytwotalents.tutorial.test.repository;

import com.fortytwotalents.tutorial.test.domain.Employee;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Profile("in-memory")
@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {

    List<Employee> employeeList = new ArrayList<>();

    @PostConstruct
    void init() {
        employeeList.add(new Employee("John", "Doe"));
        employeeList.add(new Employee("Carmen", "Bianchi"));
        employeeList.add(new Employee("Enrico", "Schmidt"));
    }

    @Override
    public Employee findByLastName(String lastName) {
        return employeeList.stream()
                .filter(employee -> lastName.equals(employee.getLastName())).collect(Collectors.toList())
                .get(0);
    }

    @Override
    public List<Employee> findAll() {
        return employeeList;
    }
}
