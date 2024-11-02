package ru.dmitriev.NauJavaSpring.entity;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dmitriev.NauJavaSpring.entity.Role;
import ru.dmitriev.NauJavaSpring.entity.User;
import ru.dmitriev.NauJavaSpring.repository.RoleRepository;
import ru.dmitriev.NauJavaSpring.repository.UserRepository;
import ru.dmitriev.NauJavaSpring.services.RoleService;

@SpringBootTest
class RoleTest {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleTest(RoleService roleService,
                    UserRepository userRepository,
                    RoleRepository roleRepository) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Тестирование удаления роли вместе с пользователями
     */
    @Test
    void testDeleteRoleInTx() {
        // создание роли
        Role adminRole = new Role();
        adminRole.setTitle(UUID.randomUUID().toString());
        roleRepository.save(adminRole);
        // создание пользователя 1
        User user1 = new User();
        user1.setName(UUID.randomUUID().toString());
        user1.setRole(adminRole);
        userRepository.save(user1);
        // создание пользователя 2
        User user2 = new User();
        user2.setName(UUID.randomUUID().toString());
        user2.setRole(adminRole);
        userRepository.save(user2);
        // удаление роли
        roleService.deleteRole(adminRole.getTitle());
        // проверка отсутствия роли в БД
        Optional<Role> foundRole = roleRepository.findById(adminRole.getId());
        Assertions.assertTrue(foundRole.isEmpty());
        // проверка отсутствия пользователя 1 в БД
        Optional<User> foundUser1 = userRepository.findById(user1.getUserId());
        Assertions.assertTrue(foundUser1.isEmpty());
        // проверка отсутствия пользователя 2 в БД
        Optional<User> foundUser2 = userRepository.findById(user2.getUserId());
        Assertions.assertTrue(foundUser2.isEmpty());
    }
}