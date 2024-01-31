package com.nht.activitytrackerserver.mapper;

import com.nht.activitytrackerserver.entity.SensorData;
import com.nht.activitytrackerserver.model.sensor.SensorDataDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SensorDataMapper {
    SensorDataDto sensorDataToDto(SensorData sensorData);
    List<SensorDataDto> sensorDataToDto(List<SensorData> sensorData);

    SensorData sensorDataDtoToEntity(SensorDataDto sensorDataDto);
    List<SensorData> sensorDataDtoToEntity(List<SensorDataDto> sensorDataDto);
}
