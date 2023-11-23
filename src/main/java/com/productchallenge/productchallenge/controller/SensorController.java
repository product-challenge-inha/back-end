package com.productchallenge.productchallenge.controller;

import com.productchallenge.productchallenge.dto.GetSensorLogsRes;
import com.productchallenge.productchallenge.service.SensorService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SensorController {

    private final SensorService sensorService;

    /**
     * 센서값 모니터링
     */
    @GetMapping("/sensor-logs")
    public ResponseEntity<List<GetSensorLogsRes>> getSensorLogs(
            @RequestParam(required = false) Long areaId,
            @RequestParam(required = false) String sensorType,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<GetSensorLogsRes> sensorLogs = sensorService.getSensorLogs(areaId, sensorType, startDate, endDate);
        return ResponseEntity.ok(sensorLogs);
    }
}
