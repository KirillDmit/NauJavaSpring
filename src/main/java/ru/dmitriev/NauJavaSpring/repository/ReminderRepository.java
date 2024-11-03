package ru.dmitriev.NauJavaSpring.repository;

import ru.dmitriev.NauJavaSpring.entity.Event;
import ru.dmitriev.NauJavaSpring.entity.Reminder;
import org.springframework.data.repository.CrudRepository;
import ru.dmitriev.NauJavaSpring.dao.ReminderCustomRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReminderRepository extends CrudRepository<Reminder, Long>, ReminderCustomRepository {

    // Метод поиска напоминаний по ID пользователя через связанную сущность Event
    @Query("SELECT r FROM Reminder r WHERE r.event.user.userId = :userId")
    List<Reminder> findRemindersByUserId(@Param("userId") Long userId);

    List<Reminder> findByEvent(Event event);
}