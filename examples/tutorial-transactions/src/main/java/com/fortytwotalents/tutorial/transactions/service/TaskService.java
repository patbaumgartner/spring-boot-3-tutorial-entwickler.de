package com.fortytwotalents.tutorial.transactions.service;

import com.fortytwotalents.tutorial.transactions.domain.Employee;
import com.fortytwotalents.tutorial.transactions.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
}
