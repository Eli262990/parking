package com.example.demo.api.base.service;

import com.example.demo.api.base.dto.ParkingCapacityDTO;
import com.example.demo.api.base.dto.ReservationDTO;
import com.example.demo.api.base.model.CarType;

public interface ParkingService {

    CarType addCarType(String carType);

    void deleteByCarType(String carType);

    ReservationDTO saveReservation(Long carTypeId, String licensePlate);

    String deleteCar(String licensePlate);

    ParkingCapacityDTO getTotalCapacity();

    ReservationDTO calculateParkingCost(String licensePlate);

}


