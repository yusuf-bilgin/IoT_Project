package com.nht.activitytrackerserver.service;

import com.nht.activitytrackerserver.entity.SensorData;
import com.nht.activitytrackerserver.mapper.SensorDataMapper;
import com.nht.activitytrackerserver.model.sensor.SensorDataDto;
import com.nht.activitytrackerserver.repository.ISensorDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorDataService implements ISensorDataService{

    private final ISensorDataRepository sensorDataRepository;
    private final SensorDataMapper sensorDataMapper;

    @Override
    public List<SensorDataDto> getAll() {
        return sensorDataMapper.sensorDataToDto(sensorDataRepository.findAll());
    }

    @Override
    public Page<SensorDataDto> getAllPaginated(Pageable pageable) {
        Page<SensorData> pagedEntity = sensorDataRepository.findAll(pageable);
        return pagedEntity.map(sensorDataMapper::sensorDataToDto);
    }

    @Override
    public SensorDataDto save(SensorDataDto sensorDataDto) {

        SensorData savedEntity = sensorDataRepository.save(sensorDataMapper.sensorDataDtoToEntity(sensorDataDto));

        return sensorDataMapper.sensorDataToDto(savedEntity);
    }


}
