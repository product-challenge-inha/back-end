package com.productchallenge.productchallenge.controller;

import com.productchallenge.productchallenge.domain.Area;
import com.productchallenge.productchallenge.domain.SensorLog;
import com.productchallenge.productchallenge.repository.SensorRepository;
import com.productchallenge.productchallenge.service.SensorLogService;
import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SensorLogController {

    @Autowired
    private SensorLogService sensorLogService;

    @GetMapping("/generateSensorLogs")
    public String generateSensorLogs() {
        sensorLogService.generateSensorLogs();
        return "Sensor logs generated successfully";
    }
}