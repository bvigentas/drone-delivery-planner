package com.bvigentas.drone_delivery.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bvigentas")
public class DroneDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneDeliveryApplication.class, args);
    }

}
