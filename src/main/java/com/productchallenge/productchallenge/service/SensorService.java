package com.productchallenge.productchallenge.service;

import com.productchallenge.productchallenge.domain.SensorLog;
import com.productchallenge.productchallenge.domain.SensorLog.SensorType;
import com.productchallenge.productchallenge.dto.GetSensorLogsRes;
import com.productchallenge.productchallenge.repository.SensorRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public List<GetSensorLogsRes> getSensorLogs(Long areaId, String sensorTypeString, LocalDateTime startDate, LocalDateTime endDate) {

        SensorType sensorType = SensorType.valueOf(sensorTypeString);

        List<SensorLog> sensorLogs = sensorRepository.findSensorLogsByAreaIdAndSensorTypeAndCreatedAtBetweenOrderByCreatedAtAsc(areaId, sensorType, startDate, endDate);

        return sensorLogs.stream()
                .map(log -> new GetSensorLogsRes(log.getValue(), log.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
