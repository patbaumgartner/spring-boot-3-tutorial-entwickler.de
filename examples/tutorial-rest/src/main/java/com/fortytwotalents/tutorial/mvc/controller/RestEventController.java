package com.fortytwotalents.tutorial.mvc.controller;

import com.fortytwotalents.tutorial.mvc.domain.Event;
import com.fortytwotalents.tutorial.mvc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RestEventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> eventList() {
        return eventService.findAllEvents();
    }

    @GetMapping("/events/{id}")
    public Event eventById(@PathVariable("id") Long id) {
        return eventService.findById(id);
    }

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public ResponseEntity<Void> createEvent(@RequestBody Event event) {
        Event newEvent = eventService.save(event);
        // Build the location URI of the new item
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{eventId}")
                .buildAndExpand(newEvent.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void updateEvent(@PathVariable("eventId") long eventId, @RequestBody Event event) {
        eventService.save(event);
    }

    @DeleteMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteEvent(@PathVariable("eventId") long eventId) {
        eventService.delete(eventService.findById(eventId));
    }


    @GetMapping("/hateoas/events")
    ResponseEntity<CollectionModel<EntityModel<Event>>> findAll() {
        List<EntityModel<Event>> employees = StreamSupport.stream(eventService.findAllEvents().spliterator(), false)
                .map(event -> EntityModel.of(event,
                        linkTo(methodOn(RestEventController.class).findOne(event.getId())).withSelfRel(),
                        linkTo(methodOn(RestEventController.class).findAll()).withRel("employees")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(employees,
                        linkTo(methodOn(RestEventController.class).findAll()).withSelfRel()));
    }

    @GetMapping("/hateoas/events/{id}")
    public ResponseEntity<EntityModel<Event>> findOne(@PathVariable long id) {
        Event event = eventService.findById(id);
        EntityModel<Event> model = EntityModel.of(event,
                linkTo(methodOn(RestEventController.class).findOne(event.getId())).withSelfRel(),
                linkTo(methodOn(RestEventController.class).findAll()).withRel("events"));

        return ResponseEntity.ok(model);
    }

}