package com.fortytwotalents.tutorial.transactions;

import java.util.List;

import com.fortytwotalents.tutorial.transactions.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fortytwotalents.tutorial.transactions.domain.Employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;


@Slf4j
@SpringBootApplication
public class TutorialTransactionApplication {

	@Bean
	public CommandLineRunner commandLineRunner(EmployeeService employeeService){
		return (args)-> {
			List<Employee> employees = employeeService.findByLastName("Bianchi");
			log.info("***");
			employees.forEach(employee -> log.info("{}", employee));
			log.info("***");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TutorialTransactionApplication.class, args);
	}
}
