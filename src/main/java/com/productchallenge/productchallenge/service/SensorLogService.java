package com.productchallenge.productchallenge.service;

import com.productchallenge.productchallenge.domain.Area;
import com.productchallenge.productchallenge.domain.SensorLog;
import com.productchallenge.productchallenge.repository.AreaRepository;
import com.productchallenge.productchallenge.repository.SensorRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.threeten.bp.ZoneId;

@Service
@Transactional
public class SensorLogService {

    private final SensorRepository sensorLogRepository;
    private final AreaRepository areaRepository;

    public SensorLogService(SensorRepository sensorLogRepository, AreaRepository areaRepository) {
        this.sensorLogRepository = sensorLogRepository;
        this.areaRepository = areaRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void generateRandomSensorLog() {
        // 센서 값을 랜덤하게 생성 (0에서 1023 사이의 값)
        long randomValue = (long) (Math.random() * 1024);

        // SensorType을 랜덤하게 선택
        SensorLog.SensorType randomSensorType = getRandomSensorType();

        // Area를 랜덤하게 선택
        Area randomArea = getRandomArea();

        // SensorLog 객체 생성
        SensorLog sensorLog = new SensorLog();
        sensorLog.setValue(randomValue);
        sensorLog.setSensorType(randomSensorType);
        sensorLog.setArea(randomArea);

        // 로그 저장
        sensorLogRepository.save(sensorLog);
    }


    private SensorLog.SensorType getRandomSensorType() {
        SensorLog.SensorType[] sensorTypes = SensorLog.SensorType.values();
        int randomIndex = (int) (Math.random() * sensorTypes.length);
        return sensorTypes[randomIndex];
    }

    private Area getRandomArea() {
        List<Area> areas = areaRepository.findAll();
        int randomIndex = (int) (Math.random() * areas.size());
        return areas.get(randomIndex);
    }
    // @Transactional
    // public void generateSensorLogs() {

    //     LocalDateTime startDateTime = LocalDateTime.of(2023, 11, 23, 0, 0);
    //     LocalDateTime currentDateTime = LocalDateTime.now();
    //     Random random = new Random();

    //     while(startDateTime.isBefore(currentDateTime)) {
    //         Timestamp timestamp = Timestamp.valueOf(startDateTime);

    //         insertSensorLog(timestamp, random.nextInt(324) + 700, "TYPE1");
    //         insertSensorLog(timestamp, random.nextInt(324) + 700, "TYPE2");
    //         insertSensorLog(timestamp, random.nextInt(324) + 700, "TYPE3");

    //         startDateTime = startDateTime.plusMinutes(30);
    //     }
    // }
    // public void insertSensorLog(Timestamp startTime,long value,String sensorTypeString) {
    //     SensorLog sensorLog = new SensorLog();
    //     sensorLog.setValue(Long.valueOf(value));

    //     Area area = getAreaById(1L);
    //     sensorLog.setArea(area);

    //     // SensorType 설정
    //     SensorLog.SensorType sensorType = SensorLog.SensorType.valueOf(sensorTypeString);
    //     sensorLog.setSensorType(sensorType);

    //     // NotificationSent 설정
    //     sensorLog.setNotificationSent(false);

    //     // CreatedAt 설정
    //     Timestamp date = startTime;
    //     sensorLog.setCreatedAt(date);

    //     // 로그 저장
    //     sensorLogRepository.save(sensorLog);
    // }

    public Area getAreaById(Long id) {
        Optional<Area> area = areaRepository.findById(1L);
        return area.orElseThrow(() -> new EntityNotFoundException("Area not found with id: " + id));
    }
}
