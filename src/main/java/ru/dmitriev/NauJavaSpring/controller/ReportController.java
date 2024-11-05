package ru.dmitriev.NauJavaSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dmitriev.NauJavaSpring.entity.Report;
import ru.dmitriev.NauJavaSpring.services.ReportService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Long> createReport() {
        // Создаем отчет и запускаем асинхронное формирование
        Long reportId = reportService.createReport();
        reportService.generateReport(reportId);
        return ResponseEntity.ok(reportId); // Возвращаем ID созданного отчета
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getReportContent(@PathVariable Long id) {
        Report report = reportService.getReportById(id);

        if (report == null) {
            return ResponseEntity.notFound().build(); // Возвращаем 404, если отчет не найден
        }

        return switch (report.getStatus()) {
            case CREATED -> ResponseEntity.ok("Отчет еще формируется. Пожалуйста, попробуйте позже.");
            case ERROR -> ResponseEntity.ok("Произошла ошибка при формировании отчета.");
            case COMPLETED -> ResponseEntity.ok(report.getContent()); // Возвращаем содержимое готового отчета
            default -> ResponseEntity.status(500).body("Неизвестный статус отчета.");
        };
    }
}
