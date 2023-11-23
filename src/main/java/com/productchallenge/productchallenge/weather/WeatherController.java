package com.productchallenge.productchallenge.weather;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/current-weather")
    public ResponseEntity<Map<String, String>> getCurrentWeather() {
        return ResponseEntity.ok(weatherService.getWeatherData());
    }
}
