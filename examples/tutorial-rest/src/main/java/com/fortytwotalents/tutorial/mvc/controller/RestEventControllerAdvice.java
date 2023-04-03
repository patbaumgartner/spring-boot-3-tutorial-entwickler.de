package com.fortytwotalents.tutorial.mvc.controller;

import com.fortytwotalents.tutorial.mvc.service.EventNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class RestEventControllerAdvice {

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void eventNotFound(EventNotFoundException e) {
        log.error("Error occured: {}", e);
    }

}