package ru.dmitriev.NauJavaSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import ru.dmitriev.NauJavaSpring.entity.Event;
import ru.dmitriev.NauJavaSpring.entity.Report;
import ru.dmitriev.NauJavaSpring.entity.ReportStatus;
import ru.dmitriev.NauJavaSpring.repository.EventRepository;
import ru.dmitriev.NauJavaSpring.repository.ReportRepository;
import ru.dmitriev.NauJavaSpring.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

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

    public Report getReportById(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        return report.orElse(null); // Вернуть отчет, если он найден, иначе вернуть null
    }

    public String getReportContent(Long reportId) {
        return reportRepository.findById(reportId)
                .map(Report::getContent)
                .orElse("Отчет не найден");
    }

    public Long createReport() {
        Report report = new Report();
        report.setStatus(ReportStatus.CREATED);
        report.setContent("Отчет находится в процессе формирования...");
        Report savedReport = reportRepository.save(report);
        return savedReport.getId();
    }

    @Transactional
    public CompletableFuture<Void> generateReport(Long reportId) {
        return CompletableFuture.runAsync(() -> {
            Report report = reportRepository.findById(reportId)
                    .orElseThrow(() -> new IllegalArgumentException("Отчет не найден"));

            try {
                long startTime = System.currentTimeMillis();

                // Задача для подсчета пользователей
                CompletableFuture<Long> userCountFuture = CompletableFuture.supplyAsync(() -> {
                    long start = System.currentTimeMillis();
                    long count = userRepository.count();
                    System.out.println("Время для подсчета пользователей: " + (System.currentTimeMillis() - start) + " ms");
                    return count;
                });

                // Задача для получения списка объектов Event
                CompletableFuture<List<String>> eventsFuture = CompletableFuture.supplyAsync(() -> {
                    long start = System.currentTimeMillis();
                    List<String> eventNames = StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                            .map(Event::getTitle)
                            .collect(Collectors.toList());
                    System.out.println("Время для получения объектов Event: " + (System.currentTimeMillis() - start) + " ms");
                    return eventNames;
                });

                // Ожидание завершения задач
                Long userCount = userCountFuture.join();
                List<String> events = eventsFuture.join();

                long totalElapsedTime = System.currentTimeMillis() - startTime;

                // Формирование содержимого отчета
                StringBuilder reportContent = new StringBuilder();
                reportContent.append("<html><body>");
                reportContent.append("<h1>Статистика приложения</h1>");
                reportContent.append("<p>Количество пользователей: ").append(userCount).append("</p>");
                reportContent.append("<p>Список объектов Event:</p><ul>");
                events.forEach(eventName -> reportContent.append("<li>").append(eventName).append("</li>"));
                reportContent.append("</ul>");
                reportContent.append("<p>Общее время формирования отчета: ").append(totalElapsedTime).append(" ms</p>");
                reportContent.append("</body></html>");

                // Сохранение содержимого и обновление статуса отчета
                report.setContent(reportContent.toString());
                report.setStatus(ReportStatus.COMPLETED);
            } catch (Exception e) {
                report.setStatus(ReportStatus.ERROR);
                report.setContent("Произошла ошибка при формировании отчета: " + e.getMessage());
                e.printStackTrace();
            } finally {
                reportRepository.save(report);
            }
        });
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }
}

