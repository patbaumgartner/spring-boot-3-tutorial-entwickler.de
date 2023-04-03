package com.fortytwotalents.tutorial.mvc.service.internal;

import com.fortytwotalents.tutorial.mvc.domain.Event;
import com.fortytwotalents.tutorial.mvc.repository.EventRepository;
import com.fortytwotalents.tutorial.mvc.service.EventNotFoundException;
import com.fortytwotalents.tutorial.mvc.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Standard implementation of the {@link EventService} API.
 *
 * @author Patrick Baumgartner
 * @since 1.0
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class StandardEventService implements EventService {

    private final EventRepository repository;

    @Autowired
    public StandardEventService(EventRepository eventRepository) {
        this.repository = eventRepository;
    }

    @Override
    public Event findById(Long id) {
        Event event = repository.findById(id);
        if (event == null) {
            throw new EventNotFoundException("Event with id: " + id + " not found.");
        }
        return event;
    }

    @Override
    public List<Event> findAllEvents() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public Event save(final Event event) {
        Assert.notNull(event, "event must not be null");
        log.debug("Saving new event: {}", event);
        return repository.save(event);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Event event) {
        repository.delete(event);
    }

}
