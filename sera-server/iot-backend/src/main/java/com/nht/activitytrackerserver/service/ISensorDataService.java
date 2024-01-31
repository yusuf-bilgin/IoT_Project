package com.nht.activitytrackerserver.service;

import com.nht.activitytrackerserver.model.sensor.SensorDataDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISensorDataService {
    List<SensorDataDto> getAll();

    Page<SensorDataDto> getAllPaginated(Pageable pageable);

    SensorDataDto save(SensorDataDto sensorDataDto);
}
