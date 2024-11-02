package ru.dmitriev.NauJavaSpring.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.dmitriev.NauJavaSpring.entity.Reminder;

import java.util.List;

@Repository
public class ReminderCustomRepositoryImpl implements ReminderCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Reminder> findRemindersByUserId(Long userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reminder> query = cb.createQuery(Reminder.class);
        Root<Reminder> reminder = query.from(Reminder.class);

        Join<Object, Object> event = reminder.join("event");
        Predicate userPredicate = cb.equal(event.get("user").get("userId"), userId);

        query.select(reminder).where(userPredicate);

        return entityManager.createQuery(query).getResultList();
    }
}
