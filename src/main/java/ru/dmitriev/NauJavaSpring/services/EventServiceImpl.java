package ru.dmitriev.NauJavaSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmitriev.NauJavaSpring.repository.EventRepositoryImpl;
import ru.dmitriev.NauJavaSpring.entity.Event;

import java.time.LocalDateTime;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepositoryImpl eventRepositoryImpl;

    @Autowired
    public EventServiceImpl(EventRepositoryImpl eventRepositoryImpl) {
        this.eventRepositoryImpl = eventRepositoryImpl;
    }

    @Override
    public void createEvent(Long id, LocalDateTime date) {
        Event newEvent = new Event();
        newEvent.setEventId(id);
        newEvent.setEventTime(date);
        eventRepositoryImpl.create(newEvent);
    }

    @Override
    public Event findById(Long id) {
        return eventRepositoryImpl.read(id);
    }

    @Override
    public void deleteById(Long id) {
        eventRepositoryImpl.delete(id);
    }

    @Override
    public void updateDate(Long id, LocalDateTime newDate) {
        Event event = new Event();
        event.setEventId(id);
        event.setEventTime(newDate);
        eventRepositoryImpl.update(event);
    }
}
