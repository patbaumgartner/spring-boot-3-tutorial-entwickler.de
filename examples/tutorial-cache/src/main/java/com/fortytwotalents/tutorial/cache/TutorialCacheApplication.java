package com.fortytwotalents.tutorial.cache;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fortytwotalents.tutorial.cache.domain.Employee;
import com.fortytwotalents.tutorial.cache.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;

@Slf4j
@EnableCaching
@SpringBootApplication
public class TutorialCacheApplication {

	@Bean
	CommandLineRunner commandLineRunner(EmployeeService employeeService) {
		return (args) -> {

			StopWatch watch = new StopWatch("Spring Cache");

			watch.start("no-cache");
			List<Employee> employees = employeeService.findByLastName("Wagner");
			log.info("***");
			employees.forEach(employee -> log.info("{}", employee));
			log.info("***");
			watch.stop();

			watch.start("with-cache");
			List<Employee> cachedEmployees = employeeService.findByLastName("Wagner");
			log.info("***");
			cachedEmployees.forEach(employee -> log.info("{}", employee));
			log.info("***");
			watch.stop();

			System.out.println("");
			System.out.println("Took total: " + watch.getTotalTimeSeconds() + " seconds");

			// prettyPrint() return a string with a table describing all tasks performed. For custom reporting, call getTaskInfo() and use the
			System.out.println("\n1. prettyPrint Result: " + watch.prettyPrint());

			// Return a short description of the total running time.
			System.out.println("2. Short Summary: " + watch.shortSummary());

			// Return the number of tasks timed.
			System.out.println("3. Total Task Count: " + watch.getTaskCount());

			// Return the name of this task.
			System.out.println("4. Last Task Name: " + watch.getLastTaskInfo().getTaskName());

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TutorialCacheApplication.class, args);
	}
}
