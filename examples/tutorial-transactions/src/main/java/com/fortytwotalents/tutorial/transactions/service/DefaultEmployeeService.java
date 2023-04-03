package com.fortytwotalents.tutorial.transactions.service;

import com.fortytwotalents.tutorial.transactions.domain.Employee;
import com.fortytwotalents.tutorial.transactions.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeService implements EmployeeService {

    private final TaskService taskService;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(timeout = 3)
    public List<Employee> findByLastName(String lastName) {
        // slowDataAccess();
        taskService.findAll();

        return employeeRepository.findByLastName(lastName);
    }

    private void slowDataAccess() {
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            // Suppress exception
        }
    }
}
