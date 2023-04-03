package com.fortytwotalents.tutorial.transactions.repository;

import com.fortytwotalents.tutorial.transactions.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcEmployeeRepository implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> findByLastName(String lastName) {
        return jdbcTemplate.query("select * from Employee WHERE LAST_NAME=?",
                (rs, rowNum) -> new Employee(rs.getLong("ID"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME")),
                lastName);
    }
}