package ru.dmitriev.NauJavaSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dmitriev.NauJavaSpring.entity.Event;
import ru.dmitriev.NauJavaSpring.repository.EventRepository;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/findByTitle")
    public List<Event> getEventsByTitle(@RequestParam String title){
        return eventRepository.findEventsByTitle(title);
    }
}