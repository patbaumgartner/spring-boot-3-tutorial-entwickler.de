package com.fortytwotalents.tutorial.cache.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fortytwotalents.tutorial.cache.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByLastName(String lastName);

	@Query("select e from Employee e where e.lastName = :lastName")
	List<Employee> findQueryByLastName(@Param("lastName") String lastName);
}