package com.example.demo.api.base.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "RESERVATION")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LICENSE_PLATE")
    private String licensePlate;

    @Column(name = "ENTRY_TIME", nullable = false)
    private LocalDateTime entryTime;

    @Column(name = "EXIT_TIME")
    private LocalDateTime exitTime;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "ParkingSpot")
    private ParkingSpot parkingSpot;

}