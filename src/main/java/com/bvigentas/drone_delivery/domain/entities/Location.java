package com.bvigentas.drone_delivery.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Location {

    private String location;

    private Integer packageWeight;

}
