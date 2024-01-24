package com.bvigentas.drone_delivery.application.service.impl;

import com.bvigentas.drone_delivery.application.service.TripOutputPresentationService;
import com.bvigentas.drone_delivery.domain.entities.Drone;
import com.bvigentas.drone_delivery.domain.entities.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripOutputPresentationServiceImpl implements TripOutputPresentationService {

    public String buildDeliveryOutput(List<Trip> deliveryPlan, List<Drone> drones) {

        StringBuilder stringBuilder = new StringBuilder();

        for (var drone: drones) {
            buildDroneOutput(deliveryPlan, stringBuilder, drone);
        }

        return stringBuilder.toString().trim();

    }

    private static void buildDroneOutput(List<Trip> deliveryPlan, StringBuilder stringBuilder, Drone drone) {
        stringBuilder.append("[").append(drone.getName()).append("]").append("\n");
        var droneRoute = deliveryPlan.stream().filter(delivery -> delivery.getDrone().getName() == drone.getName()).collect(Collectors.toList());

        buildDroneTripsOutput(stringBuilder, droneRoute);
    }

    private static void buildDroneTripsOutput(StringBuilder stringBuilder, List<Trip> droneRoute) {
        var currentTrip = 1;
        for (var delivery: droneRoute) {
            stringBuilder.append("Trip #").append(currentTrip).append("\n");

            buildTripLocations(stringBuilder, delivery);

            currentTrip++;
        }
        stringBuilder.append("\n");
    }

    private static void buildTripLocations(StringBuilder stringBuilder, Trip delivery) {
        String locations = delivery.getDeliveryLocations().stream().map(location -> "[" + location.getLocation() + "]").collect(Collectors.joining(", "));
        stringBuilder.append(locations).append("\n");
    }

}
