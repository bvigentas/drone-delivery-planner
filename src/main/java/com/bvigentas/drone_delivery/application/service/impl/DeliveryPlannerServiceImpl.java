package com.bvigentas.drone_delivery.application.service.impl;

import com.bvigentas.drone_delivery.application.service.DeliveryPlannerService;
import com.bvigentas.drone_delivery.application.service.TripOutputPresentationService;
import com.bvigentas.drone_delivery.domain.entities.Drone;
import com.bvigentas.drone_delivery.domain.entities.Location;
import com.bvigentas.drone_delivery.domain.entities.Trip;
import com.bvigentas.drone_delivery.domain.exceptions.MaximumNumberOfDronesLimitExceededException;
import com.bvigentas.drone_delivery.infrastructure.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryPlannerServiceImpl implements DeliveryPlannerService {

    private final TripOutputPresentationService outputService;

    @Override
    public String planDelivery(MultipartFile file) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(file.getInputStream())))) {

            var headerObjects = StringUtils.cleanSpecialCharacter(reader.readLine()).split(",");
            var drones = buildDrones(headerObjects);

            var locations = reader.lines()
                    .map(StringUtils::cleanSpecialCharacter)
                    .map(this::buildLocation)
                    .collect(Collectors.toList());

            var deliveryPlan = calculateMostEfficientDelivyPlan(drones, locations);

            return outputService.buildDeliveryOutput(deliveryPlan, drones);
        }
    }

    public ArrayList<Drone> buildDrones(String[] headerObjects) {
        var drones = new ArrayList<Drone>();

        for (int i = 0; i < headerObjects.length - 1; i = i + 2) {

            if (drones.size() > 100) {
                throw new MaximumNumberOfDronesLimitExceededException();
            }

            drones.add(new Drone(headerObjects[i].trim(), Integer.parseInt(headerObjects[i + 1].trim())));
        }

        return drones;
    }

    public Location buildLocation(String locationInput) {
        var locationInfo = locationInput.split(",");

        return new Location(locationInfo[0].trim(), Integer.parseInt(locationInfo[1].trim()));
    }

    private ArrayList<Trip> calculateMostEfficientDelivyPlan(List<Drone> drones, List<Location> locations) {
        var deliveries = new ArrayList<Trip>();
        final var index = new AtomicInteger(0);

        while (index.get() < locations.size()) {
            var bestDelivery = calculateBestDeliveryWithGivenLocations(drones, locations.subList(index.get(), locations.size()));

            if (bestDelivery != null) {
                index.set(index.get() + bestDelivery.getDeliveryLocations().size());
                deliveries.add(bestDelivery);
            }
        }

        return deliveries;
    }

    private Trip calculateBestDeliveryWithGivenLocations(List<Drone> drones, List<Location> locations) {
        return drones.stream()
                .map(drone -> calculateMostEfficientDeliveryForDrone(drone, locations))
                .max(Comparator.comparing(delivery -> delivery.getDeliveryLocations().size()))
                .orElseThrow(() -> new RuntimeException("An error occured while trying to find the best delivery route."));
    }

    public Trip calculateMostEfficientDeliveryForDrone(Drone drone, List<Location> remainingLocations) {
        var remainingCapacity = new AtomicInteger(drone.getMaxWeight());

        var bestLocationsForTheDrone = remainingLocations.stream()
                .takeWhile(location -> remainingCapacity.get() >= location.getPackageWeight())
                .peek(location -> remainingCapacity.set(remainingCapacity.get() - location.getPackageWeight()))
                .collect(Collectors.toList());

        return new Trip(drone, bestLocationsForTheDrone);
    }
}
