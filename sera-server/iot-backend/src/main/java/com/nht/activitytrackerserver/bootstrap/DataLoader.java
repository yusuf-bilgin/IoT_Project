package com.nht.activitytrackerserver.bootstrap;

import com.nht.activitytrackerserver.constants.Role;
import com.nht.activitytrackerserver.entity.AppUser;
import com.nht.activitytrackerserver.entity.SensorData;
import com.nht.activitytrackerserver.entity.UserRole;
import com.nht.activitytrackerserver.repository.ISensorDataRepository;
import com.nht.activitytrackerserver.repository.UserRepository;
import com.nht.activitytrackerserver.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final ISensorDataRepository sensorDataRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, UserRoleRepository userRoleRepository, ISensorDataRepository sensorDataRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.sensorDataRepository = sensorDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // load Roles and Users
        loadRolesAndUsers();

        // load sensor data
        loadSensorData();
    }

    private void loadRolesAndUsers() {

        if (userRepository.count() == 0 && userRoleRepository.count() == 0) {
            // Add user and admin roles to the database
            UserRole userRole = UserRole.builder().role(Role.USER).build();
            UserRole adminRole = UserRole.builder().role(Role.ADMIN).build();

            userRoleRepository.save(userRole);
            userRoleRepository.save(adminRole);

            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(userRole);

            Set<UserRole> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            // Add one admin and one user to the AppUser table
            AppUser adminUser = AppUser.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin.A1"))
                    .email("admin@example.com")
                    .name("Admin")
                    .surname("User")
                    .userRoles(adminRoles)
                    .build();

            AppUser normalUser = AppUser.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user.U1"))
                    .email("user@example.com")
                    .name("Normal")
                    .surname("User")
                    .userRoles(userRoles)
                    .build();

            userRepository.save(adminUser);
            userRepository.save(normalUser);
        }

    }

    private void loadSensorData() {

        if (sensorDataRepository.count() == 0) {

            SensorData sensorData1 = SensorData.builder()
                    .lightDensity(100)
                    .waterLevel(40)
                    .temperature(24.5f)
                    .humiditySoil(200)
                    .humidityWeather(120f)
                    .build();

            SensorData sensorData2 = SensorData.builder()
                    .lightDensity(110)
                    .waterLevel(45)
                    .temperature(24.8f)
                    .humiditySoil(210)
                    .humidityWeather(125f)
                    .build();

            SensorData sensorData3 = SensorData.builder()
                    .lightDensity(112)
                    .waterLevel(46)
                    .temperature(24.9f)
                    .humiditySoil(215)
                    .humidityWeather(128f)
                    .build();

            SensorData sensorData4 = SensorData.builder()
                    .lightDensity(90)
                    .waterLevel(35)
                    .temperature(28.4f)
                    .humiditySoil(250)
                    .humidityWeather(150f)
                    .build();

            SensorData sensorData5 = SensorData.builder()
                    .lightDensity(92)
                    .waterLevel(38)
                    .temperature(18f)
                    .humiditySoil(180)
                    .humidityWeather(80f)
                    .build();

            SensorData sensorData6 = SensorData.builder()
                    .lightDensity(101)
                    .waterLevel(41)
                    .temperature(21.5f)
                    .humiditySoil(201)
                    .humidityWeather(121f)
                    .build();

            sensorDataRepository.saveAll(
                    Arrays.asList(sensorData1, sensorData2, sensorData3,
                            sensorData4, sensorData5, sensorData6));
        }

    }
}
