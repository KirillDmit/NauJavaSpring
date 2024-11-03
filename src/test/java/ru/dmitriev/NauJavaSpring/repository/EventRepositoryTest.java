package ru.dmitriev.NauJavaSpring.repository;

import org.junit.jupiter.api.Test;
import ru.dmitriev.NauJavaSpring.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.dmitriev.NauJavaSpring.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testFindByEventTimeBetweenAndTitle_PositiveCase() {
        Event event = new Event();
        event.setTitle("Meeting");
        event.setDescription("Discuss project");
        event.setEventTime(LocalDateTime.now().plusDays(1));
        eventRepository.save(event);

        List<Event> events = eventRepository.findByEventTimeBetweenAndTitle(
                LocalDateTime.now(), LocalDateTime.now().plusDays(2), "Meeting");

        assertFalse(events.isEmpty());
        assertEquals("Meeting", events.get(0).getTitle());
    }

    @Test
    void testFindByEventTimeBetweenAndTitle_NegativeCase() {
        List<Event> events = eventRepository.findByEventTimeBetweenAndTitle(
                LocalDateTime.now(), LocalDateTime.now().plusDays(1), "NonExistent");

        assertTrue(events.isEmpty());
    }
}