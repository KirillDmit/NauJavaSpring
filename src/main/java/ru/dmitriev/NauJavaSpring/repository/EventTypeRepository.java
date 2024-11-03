package ru.dmitriev.NauJavaSpring.repository;

import ru.dmitriev.NauJavaSpring.entity.EventType;
import org.springframework.data.repository.CrudRepository;

public interface EventTypeRepository extends CrudRepository<EventType, Long> {

}
