package ru.dmitriev.NauJavaSpring.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Event {

    private Long id;
    private Date date;
}
