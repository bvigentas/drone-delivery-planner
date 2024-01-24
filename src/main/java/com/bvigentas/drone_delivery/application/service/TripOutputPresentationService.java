package com.bvigentas.drone_delivery.application.service;

import com.bvigentas.drone_delivery.domain.entities.Drone;
import com.bvigentas.drone_delivery.domain.entities.Trip;

import java.util.List;

public interface TripOutputPresentationService {

    String buildDeliveryOutput(List<Trip> deliveryPlan, List<Drone> drones);

}
