package com.nht.activitytrackerserver.repository;

import com.nht.activitytrackerserver.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISensorDataRepository extends JpaRepository<SensorData, Long> {
}
