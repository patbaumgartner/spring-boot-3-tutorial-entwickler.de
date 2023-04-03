package com.fortytwotalents.tutorial.mvc.controller;

import com.fortytwotalents.tutorial.mvc.domain.Event;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestTemplateTests {

    private static String BASE_URL = "http://localhost:8080/events";
    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void findAll_EventFound_ShouldReturnFoundEventEntries() {
        Event[] events = restTemplate.getForObject(BASE_URL, Event[].class);
        assertThat(events.length).isEqualTo(2);
    }

    @Test
    public void find_EventFound_ShouldReturnFoundEventEntry() {
        Event event = restTemplate.getForObject(BASE_URL + "/{id}", Event.class, 1);
        assertThat(event.getName()).isEqualTo("Web Technology Workshop");
    }

    @Test
    public void find_EventNotFound_ShouldReturnStatus404() {
        assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForObject(BASE_URL + "/{id}", Event.class, 100);
        });
    }

    @Test
    public void find_EventNotFound_ShouldReturnStatus404_ResponseEntity() {
        try {
            restTemplate.getForEntity(BASE_URL + "/{id}", Event.class, 100);
        } catch (final HttpClientErrorException e) {
            assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            assertThat(e.getResponseBodyAsString()).isEqualTo("");
        }
    }

}
