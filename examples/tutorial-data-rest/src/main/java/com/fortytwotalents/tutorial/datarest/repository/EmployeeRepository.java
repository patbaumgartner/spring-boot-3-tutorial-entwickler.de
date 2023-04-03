package com.fortytwotalents.tutorial.datarest.repository;

import com.fortytwotalents.tutorial.datarest.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "worker", path = "worker")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByLastName(@Param("lastName") String lastName);

    @Query("select e from Employee e where e.lastName = :lastName")
    List<Employee> findQueryByLastName(@Param("lastName") String lastName);
}