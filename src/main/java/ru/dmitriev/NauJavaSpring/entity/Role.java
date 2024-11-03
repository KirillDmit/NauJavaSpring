package ru.dmitriev.NauJavaSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String title;
}
