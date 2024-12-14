package com.example.demo.api.base.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "FLOOR")

public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FLOOR_NUMBER")
    private int floorNumber;

    @Column(name = "TOTAL_SPACES")
    private int totalSpaces;

    @OneToMany(mappedBy = "floor" )
    private List<ParkingSpot> parkingSpots ;

    @ManyToOne
    private Parking parking;

}

