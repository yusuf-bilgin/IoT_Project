package com.nht.activitytrackerserver.model.sensor;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SensorDataDto {

    private Long id;

    private Integer lightDensity;

    private Integer waterLevel;

    private Float temperature;

    private Integer humiditySoil;

    private Float humidityWeather;

    private LocalDateTime createdDate;
}
