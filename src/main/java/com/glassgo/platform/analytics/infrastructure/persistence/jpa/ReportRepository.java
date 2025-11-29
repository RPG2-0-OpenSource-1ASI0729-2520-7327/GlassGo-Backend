package com.glassgo.platform.analytics.infrastructure.persistence.jpa;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findBySourceId(String sourceId);
}
