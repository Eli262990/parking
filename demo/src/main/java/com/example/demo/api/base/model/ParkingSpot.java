package com.example.demo.api.base.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Data
@Table(name = "PARKING-SPOT")

public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SPOT_NUMBER")
    private int spotNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @ManyToOne
    private Floor floor;

    @OneToMany(mappedBy = "parkingSpot")
    private List<Reservation> reservationList;

}
