package ru.dmitriev.NauJavaSpring.dao;

import ru.dmitriev.NauJavaSpring.entity.Reminder;
import java.util.List;

public interface ReminderCustomRepository {

    List<Reminder> findRemindersByUserId(Long userId);
}
