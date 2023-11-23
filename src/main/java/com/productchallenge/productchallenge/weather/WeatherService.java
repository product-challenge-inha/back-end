package com.productchallenge.productchallenge.weather;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    public Map<String, String> getWeatherData() {

        //String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=8wLlBh3dIFlwPkMhibxVaURAU5Eq8Lx5T9aqkwrxf727%2FOkr236urjRdUgrrMAoQxcmwg%2FDu8Qi37YBmyU8wIA%3D%3D&numOfRows=10&pageNo=1&dataType=XML&base_date=20231123&base_time=1100&nx=55&ny=100";

        /*RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);*/

        //List<String> weatherData = new ArrayList<>();

        /*
        if (response != null && response.getBody() != null) {
            for (WeatherItem item : response.getBody().getItem()) {
                if ("PTY".equals(item.getCategory()) || "REH".equals(item.getCategory()) || "T1H".equals(item.getCategory())) {
                    weatherData.add(item.getCategory() + ": " + item.getObsrValue());
                }
            }
        }*/

        Map<String, String> weatherData = new HashMap<>();
        weatherData.put("강우", "0");
        weatherData.put("습도", "44");
        weatherData.put("온도", "11.5");

        return weatherData;
    }
}

