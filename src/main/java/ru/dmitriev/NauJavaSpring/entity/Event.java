package ru.dmitriev.NauJavaSpring.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime eventTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reminder> reminders;

    @ManyToOne
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "recurrence_id")
    private Recurrence recurrence;
}