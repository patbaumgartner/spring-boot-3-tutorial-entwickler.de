package com.fortytwotalents.tutorial.mvc.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EventNotFoundException(String message) {
        super(message);
    }

}
