package ru.dmitriev.NauJavaSpring.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dmitriev.NauJavaSpring.entity.Recurrence;
import org.springframework.data.repository.CrudRepository;

@RepositoryRestResource
public interface RecurrenceRepository extends CrudRepository<Recurrence, Long> {

}
