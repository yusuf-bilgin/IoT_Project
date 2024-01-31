package com.nht.activitytrackerserver.controller;

import com.nht.activitytrackerserver.model.sensor.SensorDataDto;
import com.nht.activitytrackerserver.service.ISensorDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sensorData")
@RequiredArgsConstructor
public class SensorDataController {

    private final ISensorDataService sensorDataService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(sensorDataService.getAll());
    }


    // api/sensorData?page=0&size=10&sort=propertyName,asc
    @GetMapping("/paged")
    public ResponseEntity getPaged(Pageable pageable) {
        return ResponseEntity.ok(sensorDataService.getAllPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody SensorDataDto sensorDataDto) {

        SensorDataDto savedDto = sensorDataService.save(sensorDataDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/sensorData/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

}
