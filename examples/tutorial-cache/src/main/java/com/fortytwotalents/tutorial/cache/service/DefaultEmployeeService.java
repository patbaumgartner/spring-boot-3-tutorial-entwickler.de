package com.fortytwotalents.tutorial.cache.service;

import com.fortytwotalents.tutorial.cache.domain.Employee;
import com.fortytwotalents.tutorial.cache.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    
    @Override
    @Cacheable("employees")
    public List<Employee> findByLastName(String lastName) {
        slowDataAccess();
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
