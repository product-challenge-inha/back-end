package com.productchallenge.productchallenge.weather;

import javax.xml.bind.annotation.*;
import java.util.List;
import lombok.Getter;

@XmlRootElement(name = "response")
public class WeatherResponse {
    private WeatherResponseBody body;

    @XmlElement(name = "body")
    public WeatherResponseBody getBody() {
        return body;
    }

    public void setBody(WeatherResponseBody body) {
        this.body = body;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class WeatherResponseBody {
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<WeatherItem> item;

    // 어노테이션 없이 게터 메소드 정의
    public List<WeatherItem> getItem() {
        return item;
    }

    public void setItem(List<WeatherItem> item) {
        this.item = item;
    }
}


@Getter
@XmlAccessorType(XmlAccessType.FIELD)
class WeatherItem {
    private String category;
    private String obsrValue;

    // getters, setters
}
