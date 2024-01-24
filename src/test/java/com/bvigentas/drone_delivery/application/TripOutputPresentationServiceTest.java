package com.bvigentas.drone_delivery.application;

import com.bvigentas.drone_delivery.application.service.impl.TripOutputPresentationServiceImpl;
import com.bvigentas.drone_delivery.domain.entities.Drone;
import com.bvigentas.drone_delivery.domain.entities.Location;
import com.bvigentas.drone_delivery.domain.entities.Trip;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TripOutputPresentationServiceTest {

    private String EXPECTED_RESULT= """
                                    [Drone A]
                                    Trip #1
                                    [LocationA], [LocationC], [LocationD]
                                    
                                    [Drone B]
                                    Trip #1
                                    [LocationB]""";
    @InjectMocks
    TripOutputPresentationServiceImpl service;

    @Test
    void shouldReturnCorrectOutPut() {

        var trips = generateTripsMock();
        var drones = Arrays.asList(Drone.builder().name("Drone A").build(), Drone.builder().name("Drone B").build());

        var result = service.buildDeliveryOutput(trips, drones);

        assertEquals(EXPECTED_RESULT, result);

    }

    private static List<Trip> generateTripsMock() {
        var trips = Arrays.asList(
                Trip.builder().drone(Drone.builder().name("Drone A").build()).deliveryLocations(
                        Arrays.asList(
                                Location.builder().location("LocationA").build(),
                                Location.builder().location("LocationC").build(),
                                Location.builder().location("LocationD").build()
                        )
                ).build(),
                Trip.builder().drone(Drone.builder().name("Drone B").build()).deliveryLocations(
                        Arrays.asList(
                                Location.builder().location("LocationB").build()
                        )
                ).build()
        );

        return trips;
    }

}
