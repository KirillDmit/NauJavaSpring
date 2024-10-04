package ru.dmitriev.NauJavaSpring.services;

import ru.dmitriev.NauJavaSpring.entity.Event;

import java.util.Date;

public interface EventService
{
    void createEvent(Long id, Date date);
    Event findById(Long id);
    void deleteById(Long id);
    void updateDate(Long id, Date newDate);
}
