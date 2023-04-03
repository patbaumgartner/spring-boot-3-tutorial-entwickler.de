package com.fortytwotalents.tutorial.mvc.service;

import com.fortytwotalents.tutorial.mvc.domain.Event;

import java.util.List;

/**
 * @author Patrick Baumgartner
 * @since 1.0
 */
public interface EventService {

    Event findById(Long id);

    List<Event> findAllEvents();

    Event save(Event event);

    void delete(Event event);

}
