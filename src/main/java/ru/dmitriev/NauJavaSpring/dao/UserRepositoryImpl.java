package ru.dmitriev.NauJavaSpring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.dmitriev.NauJavaSpring.entity.Role;
import ru.dmitriev.NauJavaSpring.entity.User;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserCustomRepository{

    private final EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findByName(String name)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate namePredicate = criteriaBuilder.equal(userRoot.get("name"), name);
        criteriaQuery.select(userRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<User> findByRole(String roleTitle)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Join<User, Role> role = userRoot.join("role", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(role.get("title"), roleTitle);
        criteriaQuery.select(userRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
