package com.productchallenge.productchallenge.controller;

import com.productchallenge.productchallenge.service.DeviceTokenService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    private final DeviceTokenService deviceTokenService;

    public DeviceController(DeviceTokenService deviceTokenService) {
        this.deviceTokenService = deviceTokenService;
    }

    @PostMapping("/registerToken")
    public ResponseEntity<?> registerToken(@RequestBody Map<String, String> requestBody) {
        String token = requestBody.get("token");
        deviceTokenService.saveToken(token);
        return ResponseEntity.ok().build();
    }
}
