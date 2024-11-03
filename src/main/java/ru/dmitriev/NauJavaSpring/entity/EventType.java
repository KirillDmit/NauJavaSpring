package ru.dmitriev.NauJavaSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "event_types")
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventTypeId;

    @Column
    private String typeName;

    @Column
    private String color;

    @OneToMany(mappedBy = "eventType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;
}
