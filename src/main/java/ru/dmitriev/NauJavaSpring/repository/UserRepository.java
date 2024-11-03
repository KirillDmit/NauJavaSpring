package ru.dmitriev.NauJavaSpring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.dmitriev.NauJavaSpring.entity.User;

public interface UserRepository extends CrudRepository<User, Long>
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
    @Query("FROM User WHERE role.title = :roleTitle")
    List<User> findByRole(String roleTitle);
}
