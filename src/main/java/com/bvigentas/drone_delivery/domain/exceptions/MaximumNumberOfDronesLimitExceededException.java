package com.bvigentas.drone_delivery.domain.exceptions;

public class MaximumNumberOfDronesLimitExceededException extends RuntimeException {

    public MaximumNumberOfDronesLimitExceededException() {
        super("Number of drones per squad has been exceded. The limit is 100.");
    }
}
