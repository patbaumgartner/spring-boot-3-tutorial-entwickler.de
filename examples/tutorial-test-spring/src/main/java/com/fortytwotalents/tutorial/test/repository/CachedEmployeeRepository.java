package com.fortytwotalents.tutorial.test.repository;

import com.fortytwotalents.tutorial.test.domain.Employee;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Profile("cache")
@Repository
public class CachedEmployeeRepository implements EmployeeRepository {

    List<Employee> employeeList = new ArrayList<>();

    @PostConstruct
    void init() {
        employeeList.add(new Employee("C-John", "C-Doe"));
        employeeList.add(new Employee("C-Carmen", "C-Bianchi"));
        employeeList.add(new Employee("C-Enrico", "C-Schmidt"));
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
