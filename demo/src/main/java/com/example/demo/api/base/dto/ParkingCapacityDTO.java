package com.example.demo.api.base.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ParkingCapacityDTO implements Serializable {

    private Integer totalSpot;
    private Integer occupiedSpot;
    private Integer availableSpot;

}




