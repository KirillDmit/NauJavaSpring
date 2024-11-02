package ru.dmitriev.NauJavaSpring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.dmitriev.NauJavaSpring.entity.Event;
import ru.dmitriev.NauJavaSpring.entity.Reminder;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReminderRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ReminderRepository reminderRepository;

    @Test
    void testFindRemindersByUserId_PositiveCase() {
        Event event = new Event();
        event.setTitle("Meeting");
        event.setDescription("Discuss project");
        event.setEventTime(LocalDateTime.now().plusDays(1));
        eventRepository.save(event);

        Reminder reminder = new Reminder();
        reminder.setReminderTime(LocalDateTime.now().plusHours(1));
        reminder.setMethod("Email");
        reminder.setEvent(event);
        reminderRepository.save(reminder);

        List<Reminder> reminders = reminderRepository.findRemindersByUserId(event.getUser().getUserId());
        assertFalse(reminders.isEmpty());
    }

    @Test
    void testFindRemindersByUserId_NegativeCase() {
        List<Reminder> reminders = reminderRepository.findRemindersByUserId(999L); // Non-existent user ID
        assertTrue(reminders.isEmpty());
    }
}