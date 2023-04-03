package com.fortytwotalents.tutorial.test.config;

import com.fortytwotalents.tutorial.test.repository.EmployeeRepository;
import com.fortytwotalents.tutorial.test.service.EmployeeService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {EmployeeRepository.class, EmployeeService.class})
public class EmployeeConfig {
}
