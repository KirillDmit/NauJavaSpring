package ru.dmitriev.NauJavaSpring.entity;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dmitriev.NauJavaSpring.entity.User;
import ru.dmitriev.NauJavaSpring.repository.UserRepository;

@SpringBootTest
class UserTest
{
    private final UserRepository userRepository;
    @Autowired
    UserTest(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    /**
     * Тестирование поиск пользователя по его имени
     */
    @Test
    void testFindUserByName()
    {
        // генерация имени пользователя
        String userName = UUID.randomUUID().toString();
        // создание пользователя
        User user = new User();
        user.setName(userName);
        userRepository.save(user);
        // поиск пользователя по имени
        User foundUser = userRepository.findByName(userName).get(0);
        // проверки
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(user.getUserId(), foundUser.getUserId());
        Assertions.assertEquals(userName, foundUser.getName());
    }
}