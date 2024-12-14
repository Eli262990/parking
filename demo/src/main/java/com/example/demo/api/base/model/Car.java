package com.example.demo.api.base.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
@Table(name = "CAR")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "licensePlate", nullable = false)
    @Pattern(regexp = "^[0-9]{2}[a-z][0-9]{3}$")
    private String licensePlate;


    @ManyToOne
    private CarType carType;
}
