package com.fortytwotalents.tutorial.test.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fortytwotalents.tutorial.test.domain.Employee;
import com.fortytwotalents.tutorial.test.repository.EmployeeRepository;
import com.fortytwotalents.tutorial.test.service.EmployeeService;
import com.fortytwotalents.tutorial.test.service.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	private final EmployeeService employeeService = new EmployeeServiceImpl();

	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		String firstName = "Carmen";
		String lastName = "Bianci";
		Employee carmen = new Employee(firstName, lastName);

		given(employeeRepository.findByLastName(lastName)).willReturn(carmen);

		Employee found = employeeService.getEmployeeByName(lastName);

		verify(employeeRepository).findByLastName(lastName);

		assertThat(found.getLastName()).isEqualTo(lastName);
	}

}
