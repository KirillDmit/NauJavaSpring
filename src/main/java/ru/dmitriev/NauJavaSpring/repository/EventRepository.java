package ru.dmitriev.NauJavaSpring.repository;

import ru.dmitriev.NauJavaSpring.entity.Event;
import org.springframework.data.repository.CrudRepository;
import ru.dmitriev.NauJavaSpring.dao.EventCustomRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long>, EventCustomRepository {

    // Метод поиска событий в заданном временном диапазоне и по названию
    List<Event> findByEventTimeBetweenAndTitle(LocalDateTime startTime, LocalDateTime endTime, String title);
}
