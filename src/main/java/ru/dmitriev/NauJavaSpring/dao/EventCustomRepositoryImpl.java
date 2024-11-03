package ru.dmitriev.NauJavaSpring.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.dmitriev.NauJavaSpring.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EventCustomRepositoryImpl implements EventCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Event> findByEventTimeBetweenAndTitle(LocalDateTime startTime, LocalDateTime endTime, String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> event = query.from(Event.class);

        Predicate timePredicate = cb.between(event.get("eventTime"), startTime, endTime);
        Predicate titlePredicate = cb.equal(event.get("title"), title);

        query.select(event).where(cb.and(timePredicate, titlePredicate));

        return entityManager.createQuery(query).getResultList();
    }
}