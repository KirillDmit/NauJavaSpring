package ru.dmitriev.NauJavaSpring.entity;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dmitriev.NauJavaSpring.repository.EventRepository;
import ru.dmitriev.NauJavaSpring.repository.UserRepository;

@SpringBootTest
class EventTest {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventTest(EventRepository eventRepository,
                     UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    /**
     * Тестирование удаления события отдельно от пользователей
     */
    @Test
    void testDeleteEventInTx() {
        // создание события
        Event event1 = new Event();
        event1.setTitle(UUID.randomUUID().toString());
        eventRepository.save(event1);
        List<Event> events = new ArrayList<>();
        events.add(event1);
        // создание пользователя 1
        User user1 = new User();
        user1.setName(UUID.randomUUID().toString());
        user1.setEvents(events);
        userRepository.save(user1);
        // создание пользователя 2
        User user2 = new User();
        user2.setName(UUID.randomUUID().toString());
        user2.setEvents(events);
        userRepository.save(user2);
        // удаление события
        eventRepository.deleteEventByTitle(event1.getTitle());
        // проверка отсутствия события в БД
        Optional<Event> foundEvent = eventRepository.findById(event1.getEventId());
        Assertions.assertTrue(foundEvent.isEmpty());
        // проверка наличия пользователя 1 в БД
        Optional<User> foundUser1 = userRepository.findById(user1.getUserId());
        Assertions.assertFalse(foundUser1.isEmpty());
        // проверка наличия пользователя 2 в БД
        Optional<User> foundUser2 = userRepository.findById(user2.getUserId());
        Assertions.assertFalse(foundUser2.isEmpty());
    }
}