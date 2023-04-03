package com.fortytwotalents.tutorial.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.fortytwotalents.tutorial.test.domain.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void whenFindByName_thenReturnEmployee() {

		// given
		Employee karl = new Employee("Karl", "Bach");
		entityManager.persist(karl);
		entityManager.flush();

		// when
		Employee found = employeeRepository.findByLastName(karl.getLastName());

		// then
		assertThat(found.getLastName()).isEqualTo(karl.getLastName());
	}

}
