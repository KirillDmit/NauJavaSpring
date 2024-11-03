package ru.dmitriev.NauJavaSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dmitriev.NauJavaSpring.repository.UserRepository;
import ru.dmitriev.NauJavaSpring.entity.User;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/findByName")
    public List<User> getUsersByName(@RequestParam String name) {
        return userRepository.findByName(name);
    }
}