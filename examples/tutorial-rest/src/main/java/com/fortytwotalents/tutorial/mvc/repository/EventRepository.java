package com.fortytwotalents.tutorial.mvc.repository;

import com.fortytwotalents.tutorial.mvc.domain.Event;

import java.util.List;

/**
 * @author Patrick Baumgartner
 * @since 1.0
 */
public interface EventRepository {

    Event findById(Long id);

    List<Event> findAll();

    Event save(Event event);

    void delete(Event event);

}
