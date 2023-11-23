package com.productchallenge.productchallenge.service;

import com.productchallenge.productchallenge.domain.Area;
import com.productchallenge.productchallenge.domain.DeviceToken;
import com.productchallenge.productchallenge.domain.SensorLog;
import com.productchallenge.productchallenge.repository.DeviceTokenRepository;
import com.productchallenge.productchallenge.repository.SensorRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
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

    public SensorMonitoringService(SensorRepository sensorRepository, DeviceTokenRepository deviceTokenRepository) {
        this.sensorRepository = sensorRepository;
        this.deviceTokenRepository = deviceTokenRepository;
    }

    @Scheduled(fixedRate = 60000) // 예: 분마다체크
    public void checkSensorDataAndSendAlerts() {
        List<SensorLog> sensors = sensorRepository.findAllByOrderByCreatedAtDesc();
        for (SensorLog sensor : sensors) {
            if (sensor.getValue() > THRESHOLD) { // && !sensor.isNotificationSent()
                // 토큰 불러오는 로직
                Optional<DeviceToken> deviceTokenOpt = Optional.ofNullable(deviceTokenRepository.findUserFCMToken());
                if (deviceTokenOpt.isPresent()) {
                    String deviceFcmToken = deviceTokenOpt.get().getToken();
                    sendPushNotification(sensor.getArea(), sensor.getCreatedAt(), deviceFcmToken);
                    sensor.setNotificationSent(true);
                    sensorRepository.save(sensor);
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
}
