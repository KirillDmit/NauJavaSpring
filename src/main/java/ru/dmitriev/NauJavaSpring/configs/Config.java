package ru.dmitriev.NauJavaSpring.configs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.dmitriev.NauJavaSpring.entity.Event;
import ru.dmitriev.NauJavaSpring.services.CommandProcessor;

@Configuration
public class Config
{
    @Autowired
    private CommandProcessor commandProcessor;

    @Bean
    public CommandLineRunner commandScanner()
    {
        return args ->
        {
            try (Scanner scanner = new Scanner(System.in))
            {
                System.out.println("Введите команду. 'exit' для выхода.");
                while (true)
                {
                    // Показать приглашение для ввода
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    // Выход из цикла, если введена команда "exit"
                    if ("exit".equalsIgnoreCase(input.trim()))
                    {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    // Обработка команды
                    commandProcessor.processCommand(input);
                }
            }
        };
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Event> eventContainer()
    {
        return new ArrayList<>();
    }
}
