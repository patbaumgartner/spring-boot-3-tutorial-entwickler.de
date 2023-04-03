package com.fortytwotalents.tutorial.test.service;

import com.fortytwotalents.tutorial.test.config.EmployeeConfig;
import com.fortytwotalents.tutorial.test.domain.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(EmployeeConfig.class)
@ActiveProfiles("in-memory")
public class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void shouldReturnEmployeeByLastName() {

        Employee employee = employeeService.getEmployeeByName("Bianchi");

        assertThat(employee.getLastName()).isEqualTo("Bianchi");
    }

}
