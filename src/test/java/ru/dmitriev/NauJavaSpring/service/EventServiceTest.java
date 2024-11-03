package ru.dmitriev.NauJavaSpring.service;

import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitriev.NauJavaSpring.entity.Event;
import ru.dmitriev.NauJavaSpring.entity.Reminder;
import ru.dmitriev.NauJavaSpring.repository.EventRepository;
import ru.dmitriev.NauJavaSpring.repository.ReminderRepository;
import jakarta.persistence.EntityManager;
import ru.dmitriev.NauJavaSpring.services.EventServiceTransactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceTest {

    @Autowired
    private EventServiceTransactional eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ReminderRepository reminderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void testCreateEventWithReminder_PositiveCase() {
        Event event = eventService.createEventWithReminder(
                "Meeting", "Discuss project", LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusHours(1), "Email");

        assertNotNull(eventRepository.findById(event.getEventId()).orElse(null));
        assertFalse(reminderRepository.findByEvent(event).isEmpty());
    }

    @Test
    @Transactional
    void testCreateEventWithReminder_NegativeCase_Rollback() {
        try {
            eventService.createEventWithReminder(
                    "FaultyEvent", "Cause rollback", LocalDateTime.now().plusDays(1),
                    null, "Email"); // reminderTime is null to cause error
            fail("Exception expected");
        } catch (TransactionSystemException e) {
            // Transaction should rollback
        }

        entityManager.clear();

        // Check that no records were saved
        List<Event> events = (List<Event>) eventRepository.findAll();
        List<Reminder> reminders = (List<Reminder>) reminderRepository.findAll();

        assertTrue(events.isEmpty());
        assertTrue(reminders.isEmpty());
    }
}