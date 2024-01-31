package com.nht.activitytrackerserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
// @Data // Warning:(17, 1) Using @Data for JPA entities is not recommended. It can cause severe performance and memory consumption issues.
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer lightDensity;

    @NotNull
    private Integer waterLevel;

    @NotNull
    private Float temperature;

    @NotNull
    private Integer humiditySoil;

    @NotNull
    private Float humidityWeather;

    @CreationTimestamp
    private LocalDateTime createdDate;
}
