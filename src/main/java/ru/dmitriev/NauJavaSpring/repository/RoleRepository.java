package ru.dmitriev.NauJavaSpring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dmitriev.NauJavaSpring.entity.Role;

@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Long>{
    void deleteByTitle(String title);
}