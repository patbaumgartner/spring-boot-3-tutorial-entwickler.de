package com.fortytwotalents.tutorial.observability;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoggingController {

    @GetMapping("/logging")
    public void generateLogs(@RequestHeader("user-agent") String userAgent){
        log.debug("Controller method invoked by {}", userAgent);
    }
}
