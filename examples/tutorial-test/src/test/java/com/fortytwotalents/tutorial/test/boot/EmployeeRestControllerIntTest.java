package com.fortytwotalents.tutorial.test.boot;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fortytwotalents.tutorial.test.TutorialTestApplication;
import com.fortytwotalents.tutorial.test.domain.Employee;
import com.fortytwotalents.tutorial.test.repository.EmployeeRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TutorialTestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class EmployeeRestControllerIntTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private EmployeeRepository repository;

	@Test
	public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {

		Long createdId = createTestEmployee("Carmen", "Bianchi");
		Long position = createdId - 1;

		mvc.perform(get("/api/employees")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[" + position + "].lastName", is("Bianchi")));
	}

	private Long createTestEmployee(String firstName, String lastName) {
		Employee carmen = new Employee(firstName, lastName);
		carmen = repository.save(carmen);
		return carmen.getId();
	}

}