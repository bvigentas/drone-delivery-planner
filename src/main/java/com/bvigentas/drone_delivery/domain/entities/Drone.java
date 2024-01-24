package com.bvigentas.drone_delivery.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Drone {

    private String name;

    private Integer maxWeight;

}
