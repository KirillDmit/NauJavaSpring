package ru.dmitriev.NauJavaSpring.repository;

import ru.dmitriev.NauJavaSpring.entity.Recurrence;
import org.springframework.data.repository.CrudRepository;

public interface RecurrenceRepository extends CrudRepository<Recurrence, Long> {

}
