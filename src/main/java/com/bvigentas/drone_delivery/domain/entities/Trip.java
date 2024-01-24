package com.bvigentas.drone_delivery.domain.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    private Drone drone;

    private List<Location> deliveryLocations;

    public void addDeliveryLocation(Location locationToAdd) {
        if (this.deliveryLocations == null) {
            this.deliveryLocations = new ArrayList<>();
        }

        this.deliveryLocations.add(locationToAdd);
    }
}
