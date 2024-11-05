package ru.dmitriev.NauJavaSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import ru.dmitriev.NauJavaSpring.entity.Event;
import ru.dmitriev.NauJavaSpring.entity.Report;
import ru.dmitriev.NauJavaSpring.repository.EventRepository;
import ru.dmitriev.NauJavaSpring.repository.ReportRepository;
import ru.dmitriev.NauJavaSpring.repository.UserRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(UserRepository userRepository, EventRepository eventRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.reportRepository = reportRepository;
    }

    public CompletableFuture<ModelAndView> generateReport() {
        long startTime = System.currentTimeMillis();

        CompletableFuture<Long> userCountFuture = CompletableFuture.supplyAsync(userRepository::count);

        CompletableFuture<List<Event>> eventsFuture = CompletableFuture.supplyAsync(() -> (List<Event>) eventRepository.findAll());

        return userCountFuture.thenCombine(eventsFuture, (userCount, events) -> {
            long usersTime = System.currentTimeMillis() - startTime;

            long eventsTime = System.currentTimeMillis() - (startTime + usersTime);

            long totalTime = System.currentTimeMillis() - startTime;

            ModelAndView modelAndView = new ModelAndView("report");
            modelAndView.addObject("userCount", userCount);
            modelAndView.addObject("events", events);
            modelAndView.addObject("usersTime", usersTime);
            modelAndView.addObject("eventsTime", eventsTime);
            modelAndView.addObject("totalTime", totalTime);

            return modelAndView;
        });
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }
}

