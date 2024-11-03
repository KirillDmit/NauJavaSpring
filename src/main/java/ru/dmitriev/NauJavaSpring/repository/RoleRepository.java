package ru.dmitriev.NauJavaSpring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dmitriev.NauJavaSpring.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    void deleteByTitle(String title);
}