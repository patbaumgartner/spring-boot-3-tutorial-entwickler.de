package com.fortytwotalents.tutorial.test.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fortytwotalents.tutorial.test.controller.EmployeeRestController;
import com.fortytwotalents.tutorial.test.domain.Employee;
import com.fortytwotalents.tutorial.test.repository.EmployeeRepository;
import com.fortytwotalents.tutorial.test.service.EmployeeService;
import com.fortytwotalents.tutorial.test.service.EmployeeServiceImpl;

@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerTest {

	@TestConfiguration
	static class ContextConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}

	@MockBean
	private EmployeeRepository repository;

	@Autowired
	private MockMvc mvc;

	@Test
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {

		Employee carmen = new Employee("Carmen", "Bianchi");

		List<Employee> allEmployees = Arrays.asList(carmen);

		given(repository.findAll()).willReturn(allEmployees);

		mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].lastName", is(carmen.getLastName())));
	}

}
