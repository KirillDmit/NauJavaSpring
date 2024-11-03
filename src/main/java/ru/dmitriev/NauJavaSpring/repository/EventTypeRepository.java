package ru.dmitriev.NauJavaSpring.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dmitriev.NauJavaSpring.entity.EventType;
import org.springframework.data.repository.CrudRepository;

@RepositoryRestResource
public interface EventTypeRepository extends CrudRepository<EventType, Long> {

}
