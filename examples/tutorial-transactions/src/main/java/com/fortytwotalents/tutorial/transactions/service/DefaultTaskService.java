package com.fortytwotalents.tutorial.transactions.service;

import com.fortytwotalents.tutorial.transactions.domain.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class DefaultTaskService implements TaskService{

    @Override
    @Transactional
    public List<Task> findAll() {
        return Collections.emptyList();
    }
}
