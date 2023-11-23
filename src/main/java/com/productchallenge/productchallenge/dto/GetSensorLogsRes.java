package com.productchallenge.productchallenge.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetSensorLogsRes {

    private final Long value;
    private final LocalDateTime recordedAt;
}
