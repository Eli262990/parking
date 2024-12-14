package com.example.demo.api.base.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "PARKING")

public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NUMBER_PHONE")
    private String numberPhone;

    @OneToMany(mappedBy = "parking")
    private List<Floor> floors;


}


