package com.productchallenge.productchallenge.service;

import com.productchallenge.productchallenge.domain.Area;
import com.productchallenge.productchallenge.domain.DeviceToken;
import com.productchallenge.productchallenge.domain.SensorLog;
import com.productchallenge.productchallenge.repository.AreaRepository;
import com.productchallenge.productchallenge.repository.DeviceTokenRepository;
import com.productchallenge.productchallenge.repository.SensorRepository;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SensorMonitoringService {

    private static final double THRESHOLD = 500.0;

    private final SensorRepository sensorRepository;
    private final DeviceTokenRepository deviceTokenRepository;
    private final AreaRepository areaRepository;

    @Autowired
    public SensorMonitoringService(SensorRepository sensorRepository, DeviceTokenRepository deviceTokenRepository,
                                   AreaRepository areaRepository) {
        this.sensorRepository = sensorRepository;
        this.deviceTokenRepository = deviceTokenRepository;
        this.areaRepository = areaRepository;
    }

    /**
     * 유해물질 초과 알림
     * */
    @Scheduled(fixedRate = 60000) // 예: 분마다체크
    public void checkSensorDataAndSendAlerts() {
        List<SensorLog> sensors = sensorRepository.findAllByOrderByCreatedAtDesc();
        for (SensorLog sensor : sensors) {
            if (sensor.getValue() > THRESHOLD && !sensor.isNotificationSent()) { // && !sensor.isNotificationSent() 중복제거
                // 토큰 불러오는 로직
                Optional<DeviceToken> deviceTokenOpt = Optional.ofNullable(deviceTokenRepository.findUserFCMToken());
                if (deviceTokenOpt.isPresent()) {
                    String deviceFcmToken = deviceTokenOpt.get().getToken();
                    sendPushNotification(sensor.getArea(), sensor.getCreatedAt(), deviceFcmToken);
                    sensor.setNotificationSent(true);
                    sensorRepository.save(sensor);
                    break; // 1개만 푸시알람 뜨도록
                }
            }
        }
    }

    private void sendPushNotification(Area area, Date createdAt,String deviceFcmToken) {
        // 날짜 포맷팅
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(createdAt);

        // 메시지 구성
        Message message = Message.builder()
                .setToken(deviceFcmToken)
                .putData("title", "유해물질 초과 알림")
                .putData("body", area + " 지역에서 " + "유해물질이 과도하게 탐지되었습니다. " + formattedDate)
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Scheduled(fixedRate = 60000*30)
    public void 분30마다_체크해서_데이터보내기() {

        LocalDateTime currentDateTime = LocalDateTime.now();
        Random random = new Random();

        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        insertSensorLog(timestamp, random.nextInt(324) + 700, "TYPE1");
        insertSensorLog(timestamp, random.nextInt(324) + 700, "TYPE2");
        insertSensorLog(timestamp, random.nextInt(324) + 700, "TYPE3");

    }
    public void insertSensorLog(Timestamp startTime,long value,String sensorTypeString) {
        SensorLog sensorLog = new SensorLog();
        sensorLog.setValue(Long.valueOf(value));

        Area area = getAreaById(1L);
        sensorLog.setArea(area);

        // SensorType 설정
        SensorLog.SensorType sensorType = SensorLog.SensorType.valueOf(sensorTypeString);
        sensorLog.setSensorType(sensorType);

        // NotificationSent 설정
        sensorLog.setNotificationSent(false);

        // CreatedAt 설정
        Timestamp date = startTime;
        sensorLog.setCreatedAt(date);

        // 로그 저장
        sensorRepository.save(sensorLog);
    }

    public Area getAreaById(Long id) {
        Optional<Area> area = areaRepository.findById(1L);
        return area.orElseThrow(() -> new EntityNotFoundException("Area not found with id: " + id));
    }
}