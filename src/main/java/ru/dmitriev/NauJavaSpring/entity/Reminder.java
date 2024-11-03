package ru.dmitriev.NauJavaSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reminders")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reminderId;

    @Column
    private LocalDateTime reminderTime;

    @Column
    private String method;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}