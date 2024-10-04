package ru.dmitriev.NauJavaSpring.entity;

import java.util.Date;

public class Event {

    private Long id;
    private Date date;
    private Integer capacity;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
