package ru.dmitriev.NauJavaSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmitriev.NauJavaSpring.entity.User;
import ru.dmitriev.NauJavaSpring.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
