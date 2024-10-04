package ru.dmitriev.NauJavaSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class CommandProcessor
{
    private final EventService eventService;

    @Autowired
    public CommandProcessor(EventService eventService)
    {
        this.eventService = eventService;
    }
    public void processCommand(String input)
    {
        String[] cmd = input.split(" ");
        switch (cmd[0])
        {
            case "create" ->
            {
                eventService.createEvent(Long.valueOf(cmd[1]), Date.valueOf(cmd[2]));
                System.out.println("Событие успешно добавлено...");
            }
            // …. здесь логика Обработки других команды
            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}

