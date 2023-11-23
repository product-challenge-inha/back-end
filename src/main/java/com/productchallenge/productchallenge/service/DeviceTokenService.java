package com.productchallenge.productchallenge.service;

import com.productchallenge.productchallenge.domain.DeviceToken;
import com.productchallenge.productchallenge.repository.DeviceTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceTokenService {

    private final DeviceTokenRepository deviceTokenRepository;

    public DeviceTokenService(DeviceTokenRepository deviceTokenRepository) {
        this.deviceTokenRepository = deviceTokenRepository;
    }

    @Transactional
    public void saveToken(String token) {
        DeviceToken deviceToken = new DeviceToken();

        deviceToken.setToken(token);
        deviceTokenRepository.save(deviceToken);
    }

}