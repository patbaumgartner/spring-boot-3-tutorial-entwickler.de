package com.fortytwotalents.tutorial.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fortytwotalents.tutorial.data.domain.Employee;
import com.fortytwotalents.tutorial.data.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class TutorialDataApplication {

	@Bean
	public CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository){
		return (args)->{
			List<Employee> employees = employeeRepository.findAll();
			log.info("***");
			employees.forEach(employee -> log.info("{}", employee));
			log.info("***");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TutorialDataApplication.class, args);
	}
}
