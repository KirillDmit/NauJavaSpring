package ru.dmitriev.NauJavaSpring.dao;

import java.util.List;
import ru.dmitriev.NauJavaSpring.entity.User;

public interface UserCustomRepository
{
    /**
     * Находит всех пользователей с заданным именем
     * @param name имя пользователя
     */
    List<User> findByName(String name);
    /**
     * Находит всех пользователей с заданным название роли
     * @param roleTitle наименование роли
     */
    List<User> findByRole(String roleTitle);
}
