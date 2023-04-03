package com.fortytwotalents.tutorial.mvc.repository.internal;

import com.fortytwotalents.tutorial.mvc.domain.Event;
import com.fortytwotalents.tutorial.mvc.repository.EventRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * Hibernate-based implementation of the {@link EventRepository} API.
 *
 * @author Patrick Baumgartner
 * @since 1.0
 */
@Repository
public class JpaEventRepository implements EventRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Event findById(Long id) {
        Assert.notNull(id, "id must not be null");
        return entityManager.find(Event.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> findAll() {
        return entityManager.createQuery("from Event").getResultList();
    }

    @Override
    public Event save(Event event) {
        Assert.notNull(event, "event must not be null");
        return entityManager.merge(event);
    }

    @Override
    public void delete(Event event) {
        Assert.notNull(event, "event must not be null");
        entityManager.remove(event);
    }

}
