package com.fortytwotalents.tutorial.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fortytwotalents.tutorial.test.domain.Employee;
import com.fortytwotalents.tutorial.test.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class TutorialTestApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = employeeRepository.findAll();
		log.info("***");
		employees.forEach(employee -> log.info("{}", employee));
		log.info("***");
	}

	public static void main(String[] args) {
		SpringApplication.run(TutorialTestApplication.class, args);
	}
}
