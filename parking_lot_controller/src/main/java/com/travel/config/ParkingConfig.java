package com.travel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParkingConfig {
    @Value("${parking.id}")
    private String PARKING_ID;

    @Value("${parking.name}")
    private String PARKING_NAME;

    @Value("${parking.size}")
    private Integer PARKING_SIZE;
}
