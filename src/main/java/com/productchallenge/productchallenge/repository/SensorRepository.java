package com.productchallenge.productchallenge.repository;

import com.productchallenge.productchallenge.domain.SensorLog;
import com.productchallenge.productchallenge.domain.SensorLog.SensorType;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<SensorLog, Long> {

    List<SensorLog> findSensorLogsByAreaIdAndSensorTypeAndCreatedAtBetweenOrderByCreatedAtAsc(Long area_id,
                                                                                              SensorType sensorType,
                                                                                              LocalDateTime startDate,
                                                                                                LocalDateTime endDate);

    List<SensorLog> findAllByOrderByCreatedAtDesc();
}
