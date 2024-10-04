package ru.dmitriev.NauJavaSpring.dto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dmitriev.NauJavaSpring.entity.Event;

@Component
public class EventRepository implements CrudRepository<Event, Long>
{
    private final List<Event> eventContainer;
    @Autowired
    public EventRepository(List<Event> eventContainer)
    {
        this.eventContainer = eventContainer;
    }
    @Override
    public void create(Event event)
    {
        // здесь логика добавления сущности в eventContainer
    }
    @Override
    public Event read(Long id)
    {
        // здесь логика получения сущности по id из eventContainer
        return new Event();
    }
    @Override
    public void update(Event event)
    {
        // здесь логика обновления сущности в eventContainer
    }
    @Override
    public void delete(Long id)
    {
        // здесь логика удаления сущности из eventContainer
    }
}
