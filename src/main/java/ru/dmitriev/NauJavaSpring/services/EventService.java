package ru.dmitriev.NauJavaSpring.services;

import ru.dmitriev.NauJavaSpring.entity.Event;

import java.time.LocalDateTime;

public interface EventService
{
    void createEvent(Long id, LocalDateTime date);
    Event findById(Long id);
    void deleteById(Long id);
    void updateDate(Long id, LocalDateTime newDate);
}
