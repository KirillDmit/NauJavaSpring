package ru.dmitriev.NauJavaSpring.dao;

import ru.dmitriev.NauJavaSpring.entity.Event;
import java.time.LocalDateTime;
import java.util.List;

public interface EventCustomRepository {

    List<Event> findByEventTimeBetweenAndTitle(LocalDateTime startTime, LocalDateTime endTime, String title);
}
