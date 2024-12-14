package com.example.demo.api.base.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CAR_TYPE")
public class CarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CAR-TYPE")
    private String carType;

//    @OneToMany(mappedBy = "carType")
//    private List<Car> cars;

}
