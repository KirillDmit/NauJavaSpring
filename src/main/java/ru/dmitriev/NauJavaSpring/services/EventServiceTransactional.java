package ru.dmitriev.NauJavaSpring.services;

import ru.dmitriev.NauJavaSpring.entity.Event;
import ru.dmitriev.NauJavaSpring.entity.Reminder;
import ru.dmitriev.NauJavaSpring.repository.EventRepository;
import ru.dmitriev.NauJavaSpring.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EventServiceTransactional {
    private final EventRepository eventRepository;
    private final ReminderRepository reminderRepository;

    @Autowired
    public EventServiceTransactional(EventRepository eventRepository, ReminderRepository reminderRepository) {
        this.eventRepository = eventRepository;
        this.reminderRepository = reminderRepository;
    }

    @Transactional
    public Event createEventWithReminder(String title, String description, LocalDateTime eventTime, LocalDateTime reminderTime, String reminderMethod) {
        // Создание объекта события
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setEventTime(eventTime);

        // Сохранение события
        Event savedEvent = eventRepository.save(event);

        // Создание объекта напоминания, связанного с событием
        Reminder reminder = new Reminder();
        reminder.setReminderTime(reminderTime);
        reminder.setMethod(reminderMethod);
        reminder.setEvent(savedEvent);

        // Сохранение напоминания
        reminderRepository.save(reminder);

        return savedEvent;
    }
}