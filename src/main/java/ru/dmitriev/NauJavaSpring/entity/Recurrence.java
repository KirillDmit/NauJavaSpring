package ru.dmitriev.NauJavaSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "recurrences")
public class Recurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recurrenceId;

    @Column
    private String frequency;

    @Column
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "recurrence", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;
}
