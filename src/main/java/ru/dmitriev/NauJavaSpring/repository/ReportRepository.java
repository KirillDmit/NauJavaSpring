package ru.dmitriev.NauJavaSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmitriev.NauJavaSpring.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}