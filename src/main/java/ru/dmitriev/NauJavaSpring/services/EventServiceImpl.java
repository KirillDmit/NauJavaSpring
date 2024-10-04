package ru.dmitriev.NauJavaSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.dmitriev.NauJavaSpring.dto.EventRepository;
import ru.dmitriev.NauJavaSpring.entity.Event;

import java.util.Date;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Long id, Date date) {
        Event newEvent = new Event();
        newEvent.setId(id);
        newEvent.setDate(date);
        eventRepository.create(newEvent);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.delete(id);
    }

    @Override
    public void updateDate(Long id, Date newDate) {
        Event event = new Event();
        event.setId(id);
        event.setDate(newDate);
        eventRepository.update(event);
    }
}
