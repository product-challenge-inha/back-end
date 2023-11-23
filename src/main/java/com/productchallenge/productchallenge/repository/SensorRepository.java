package com.productchallenge.productchallenge.repository;

import com.productchallenge.productchallenge.domain.SensorLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<SensorLog, Long> {
    // 최신순
    List<SensorLog> findAllByOrderByCreatedAtDesc();
}